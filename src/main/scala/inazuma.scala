
import org.apache.spark.{SparkConf, SparkContext}
import org.atilika.kuromoji.{Token, Tokenizer}

/**
 * Created by AKB428
 */
object inazuma {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Inazuma Application")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    val input = sc.textFile(args(0)) // hdfs://

    var printRankingNum = 20
    if (args.length == 2) {
      printRankingNum = args(1).toInt
    }

    // kuromoji(形態要素解析)で日本語解析
    val words = input.flatMap(x => {
      val tokens : java.util.List[Token] = CustomTokenizer.tokenize(x)
      val features : scala.collection.mutable.ArrayBuffer[String] = new collection.mutable.ArrayBuffer[String]()

      for(index <- 0 to tokens.size()-1){
        // 二文字以上の単語を抽出
        if(tokens.get(index).getSurfaceForm().length() >= 2) {
          features += tokens.get(index).getAllFeatures()
        }
      }

      (features)
    })

    // ソート方法を定義（必ずソートする前に定義）
    implicit val sortIntegersByString = new Ordering[Int] {
      override def compare(a: Int, b: Int) = a.compare(b)*(-1)
    }

    // ソート
    val result = words.map(x => (x,1)).reduceByKey((x,y) => x + y).sortBy(_._2)

    // ソート結果から上位を取得
    for (r <- result.take(printRankingNum)) {
      println(r.toString())
    }

    sc.stop

  }
}

object CustomTokenizer {
  def tokenize(text: String): java.util.List[Token]  = {
    Tokenizer.builder().mode(Tokenizer.Mode.SEARCH)
      .userDictionary("./dictionary/anime_2015_2Q.txt")
      .build().tokenize(text)
  }
}

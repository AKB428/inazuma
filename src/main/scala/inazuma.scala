
import org.apache.spark.{SparkConf, SparkContext}
import org.atilika.kuromoji.{Token, Tokenizer}
import java.io.PrintWriter
/**
 * Created by AKB428
 */
object inazuma {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Inazuma Application")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    val input = sc.textFile(args(0)) // hdfs://

    var printRankingNum = 50
    var dictFilePath = "./dictionary/blank.txt"

    if (args.length >= 2) {
      dictFilePath = args(1)
    }

    if (args.length == 3) {
      printRankingNum = args(2).toInt
    }


    // kuromoji(形態要素解析)で日本語解析
    val words = input.flatMap(x => {

      val tokens : java.util.List[Token] = CustomTokenizer2.tokenize(x, dictFilePath)
      val features : scala.collection.mutable.ArrayBuffer[String] = new collection.mutable.ArrayBuffer[String]()

      for(index <- 0 to tokens.size()-1){
        // 二文字以上の単語を抽出
        if(tokens.get(index).getSurfaceForm().length() >= 2) {
         // features += tokens.get(index).getSurfaceForm + "[" + tokens.get(index).getPartOfSpeech + "]"

         if (tokens.get(index).getAllFeaturesArray()(0) == "名詞" && (tokens.get(index).getAllFeaturesArray()(1) == "一般" || tokens.get(index).getAllFeaturesArray()(1) == "固有名詞"))
          {
            features += tokens.get(index).getSurfaceForm
          } else if (tokens.get(index).getPartOfSpeech == "カスタム名詞" || tokens.get(index).getPartOfSpeech == "wiki") {
           // println(tokens.get(index).getPartOfSpeech)
           // println(tokens.get(index).getSurfaceForm)
           features += tokens.get(index).getSurfaceForm
        }

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
      println(r._1 + "    " + r._2)
    }

    // 結果をCSVファイルに保存
    val out = new PrintWriter("data.csv")
    for (r <- result.take(printRankingNum)) {
      out.println(r._1 + "," + r._2)
    }
    out.close

    sc.stop


  }
}

object CustomTokenizer2 {

  def tokenize(text: String, dictPath: String): java.util.List[Token]  = {
    Tokenizer.builder().mode(Tokenizer.Mode.NORMAL)
      .userDictionary(dictPath)
      .build().tokenize(text)
  }
}

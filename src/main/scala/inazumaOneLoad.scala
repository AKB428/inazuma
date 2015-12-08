
import org.apache.spark.{SparkConf, SparkContext}
import org.atilika.kuromoji.{Token, Tokenizer}
import java.io.PrintWriter
import scala.io._
import collection.JavaConversions._

/**
  * Created by AKB428
  */
object inazumaOneLoad {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Inazuma Application")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    val source = Source.fromFile(args(0), "UTF-8")

    var printRankingNum = 50
    var dictFilePath = "./dictionary/blank.txt"

    if (args.length >= 2) {
      dictFilePath = args(1)
    }

    if (args.length == 3) {
      printRankingNum = args(2).toInt
    }

    val tokens : java.util.List[Token] = CustomTokenizer4.tokenize(source.mkString, dictFilePath)

    println("tokens load end")
    println(tokens.size())


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


    sc.stop


  }
}

object CustomTokenizer4 {

  def tokenize(text: String, dictPath: String): java.util.List[Token]  = {
    Tokenizer.builder().mode(Tokenizer.Mode.NORMAL)
      .userDictionary(dictPath)
      .build().tokenize(text)
  }
}

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType};
import scala.runtime.ScalaRunTime._

/**
  * Created by AKB428
  */

// https://github.com/databricks/spark-csv

object inazumaTwitterDataToCsv {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Inazuma TW CSV")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "false")
      .load(args(0))

    // id	name	tweet_text	source	retweet_count	favorite_count	created_a
    // Array(C0, C1, C2, C3, C4, C5, C6, C7)
    println(stringOf(df.columns))

    val selectedData = df.select("C0", "C1", "C2")
    selectedData.show()

    df.groupBy("C0").count().show()

    sc.stop


  }
}



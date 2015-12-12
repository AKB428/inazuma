name := "inazuma"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7"
)

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"
libraryDependencies += "com.databricks" % "spark-csv_2.11" % "1.3.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.5.2"
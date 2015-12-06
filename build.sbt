name := "inazuma"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7"
)

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"
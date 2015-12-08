name := "inazuma_submit"

version := "1.0"

scalaVersion := "2.10.5"

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7"
)

//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"


libraryDependencies ++= Seq(
  ("org.apache.spark" %% "spark-core" % "1.5.2").
    exclude("org.mortbay.jetty", "servlet-api").
    exclude("com.google.guava","guava").
    exclude("org.apache.hadoop","hadoop-yarn-api").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("commons-collections", "commons-collections").
    exclude("commons-logging", "commons-logging").
    exclude("org.spark-project.spark", "unused").
    exclude("com.esotericsoftware.minlog", "minlog")
)

mainClass in assembly := Some("inazumaSubmit")
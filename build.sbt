ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "avro-parquet-test",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.2",
      "org.apache.commons" % "commons-csv" % "1.9.0",
      "org.apache.avro" % "avro" % "1.11.0",
      "org.apache.parquet" % "parquet-avro" % "1.8.1",
      "org.apache.parquet" % "parquet-hadoop" % "1.8.1",
      "org.apache.hadoop" % "hadoop-common" % "3.2.3",
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "org.slf4j" % "slf4j-simple" % "1.7.36",
      "com.sksamuel.avro4s" %% "avro4s-core" % "4.1.1"
    ),
    assembly / assemblyMergeStrategy := {
      case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
      case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
      case "reference.conf" => MergeStrategy.concat
      case x: String if x.contains("UnusedStubClass.class") => MergeStrategy.first
      case _ => MergeStrategy.first
    }
  )

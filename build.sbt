import _root_.sbt.Keys._

lazy val projectName = "scala-bst"
lazy val projectVersion = "0.0.1"

lazy val commonSettings = Seq(
  resolvers ++= Seq(
    "Sonatype OSS" at "https://oss.sonatype.org/content/repositories/releases/"
  ),
  organization := "ekiaa",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).aggregate(library)

lazy val library =
  project.in(file("library")).
    configs(IntegrationTest).
    enablePlugins(JmhPlugin).
    settings(commonSettings: _*).
    settings(Defaults.itSettings: _*).
    settings(
      scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
      version := projectVersion,
      name := projectName,
      libraryDependencies ++= Seq(
        "com.typesafe" % "config" % "1.3.0",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
        "ch.qos.logback" % "logback-classic" % "1.1.3",
        "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test",
        "org.scalatest" %% "scalatest" % "2.2.4" % "it,test"
      ),
      parallelExecution in Test := false
    )
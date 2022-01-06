name := """x-guardian-core"""
organization := "com.xguardian"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.7"

val circeVersion     = "0.12.2"

libraryDependencies ++= Seq(
  guice,
  // json
  "com.dripower" %% "play-circe"           % "2814.1",
  "io.circe"     %% "circe-core"           % circeVersion,
  "io.circe"     %% "circe-generic"        % circeVersion,
  "io.circe"     %% "circe-parser"         % circeVersion,
  "io.circe"     %% "circe-generic-extras" % circeVersion,
  // mongo
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
  // misc
  "io.netty" % "netty-all" % "4.1.72.Final",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)

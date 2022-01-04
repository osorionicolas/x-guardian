name := """x-guardian-core"""
organization := "com.xguardian"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.7"

libraryDependencies ++= Seq(
  guice,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.2.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)

name := """x-guardian-core"""
organization := "xmentor"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, sbtdocker.DockerPlugin)

scalaVersion := "2.13.7"

lazy val circeVersion     = "0.12.2"

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
  "net.logstash.logback" % "logstash-logback-encoder" % "5.1",
  "io.netty" % "netty-all" % "4.1.72.Final",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)

// imageName:Tag value
docker / imageNames := Seq(
  ImageName(s"${organization.value}/${name.value}:latest")
)

docker / dockerfile := {
  val appDir: File = stage.value
  val targetDir = "/opt/docker"

  new Dockerfile {
    from("openjdk:8-jre-slim")
    expose(9000)
    run("apt", "update")
    run("apt", "-y", "upgrade")
    run("apt", "-y", "install", "curl")
    copy(appDir, targetDir)
    run("chmod", "-R", "755", s"$targetDir")
    entryPoint(s"$targetDir/conf/wrapper.sh")
  }
}
package global

import com.google.inject.{AbstractModule, Provides}
import models.configurations.MongoConfiguration
import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.api.{Configuration, Environment}

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {

  val _ = environment

  override def configure(): Unit = {}

  @Provides
  def mongoConfiguration(): MongoConfiguration =
    MongoConfiguration(
      user = configuration.get[String]("mongo.user"),
      password = configuration.get[String]("mongo.password"),
      database = configuration.get[String]("mongo.database")
    )

  @Provides
  def mongoDatabase(mongoConfiguration: MongoConfiguration): MongoDatabase = {
    val uri: String =
      s"mongodb+srv://${mongoConfiguration.user}:${mongoConfiguration.password}@x-mentor.gs4ad.mongodb.net/${mongoConfiguration.database}?retryWrites=true&w=majority"
    System.setProperty("org.mongodb.async.type", "netty")
    val client: MongoClient = MongoClient(uri)
    client.getDatabase(mongoConfiguration.database)
  }
}

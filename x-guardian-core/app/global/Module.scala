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
      url = configuration.get[String]("mongo.url"),
      database = configuration.get[String]("mongo.database")
    )

  @Provides
  def mongoDatabase(mongoConfiguration: MongoConfiguration): MongoDatabase = {
    System.setProperty("org.mongodb.async.type", "netty")
    val client: MongoClient = MongoClient(mongoConfiguration.url)
    client.getDatabase(mongoConfiguration.database)
  }
}

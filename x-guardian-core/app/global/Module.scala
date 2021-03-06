package global

import akka.actor.ActorSystem
import com.google.inject.name.Named
import com.google.inject.{AbstractModule, Provides}
import global.Configurations.MONGO_DISPATCHER
import models.AlertType
import models.configurations.MongoConfiguration
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.api.libs.concurrent.CustomExecutionContext
import play.api.{Configuration, Environment}
import repositories.ALERT_TYPE_COLLECTION

import javax.inject.Singleton
import scala.concurrent.ExecutionContext

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {

  val _ = environment

  override def configure(): Unit = {}

  @Provides @Singleton @Named(MONGO_DISPATCHER)
  def protocolExecutionContext(system: ActorSystem): ExecutionContext =
    new CustomExecutionContext(system, MONGO_DISPATCHER) {}

  @Provides
  def mongoConfiguration(): MongoConfiguration =
    MongoConfiguration(
      url = configuration.get[String]("mongo.url"),
      database = configuration.get[String]("mongo.database")
    )

  @Provides
  @Singleton
  def mongoDatabase(mongoConfiguration: MongoConfiguration): MongoDatabase = {
    System.setProperty("org.mongodb.async.type", "netty")
    val client: MongoClient = MongoClient(mongoConfiguration.url)
    client.getDatabase(mongoConfiguration.database)
  }
}

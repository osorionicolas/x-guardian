package global

import akka.actor.ActorSystem
import com.google.inject.{AbstractModule, Provides}
import com.google.inject.name.Named
import global.Configurations.MONGO_DISPATCHER
import models.configurations.MongoConfiguration
import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.api.libs.concurrent.CustomExecutionContext
import play.api.{Configuration, Environment}

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
  def mongoDatabase(mongoConfiguration: MongoConfiguration): MongoDatabase = {
    System.setProperty("org.mongodb.async.type", "netty")
    val client: MongoClient = MongoClient(mongoConfiguration.url)
    client.getDatabase(mongoConfiguration.database)
  }
}

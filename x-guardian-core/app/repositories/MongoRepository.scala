package repositories

import akka.Done
import global.ApplicationResult
import global.Configurations.MONGO_DISPATCHER
import org.mongodb.scala.{MongoDatabase, _}
import play.api.Logging
import utils.mongo.MongoDocumentDecoder._
import utils.mongo.MongoDocumentEncoder.MongoDocumentEncoder
import utils.mongo.MongoDocumentEncoderSyntax.MongoDocumentEncoderOps

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class MongoRepository @Inject()(mongoDatabase: MongoDatabase)(implicit @Named(MONGO_DISPATCHER) ec: ExecutionContext)
    extends Logging {

  def getCollection[T: MongoDocumentDecoder](collection: String): ApplicationResult[Seq[T]] =
    mongoDatabase
      .getCollection(collection)
      .find(Document())
      .toFuture()
      .map { documents =>
        documents.map(doc => parse[T](doc)).collect {
          case Right(entity) => entity
        }
      }
      .map(Right(_))

  def insert[T: MongoDocumentEncoder](entity: T, collection: String): ApplicationResult[Done] =
    mongoDatabase
      .getCollection(collection)
      .insertOne(entity.asDocument)
      .toFuture()
      .map { _ =>
        logger.debug("Insert success")
        Right(Done)
      }
}

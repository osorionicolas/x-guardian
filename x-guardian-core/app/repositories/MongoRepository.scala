package repositories

import global.ApplicationResult
import global.Configurations.MONGO_DISPATCHER
import models.AlertType
import org.mongodb.scala.{MongoCollection, MongoDatabase}

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import org.mongodb.scala
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import utils.mongo.MongoDocumentDecoder._

@Singleton
class MongoRepository @Inject()(mongoDatabase: MongoDatabase)(implicit @Named(MONGO_DISPATCHER) ec: ExecutionContext) {

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
}

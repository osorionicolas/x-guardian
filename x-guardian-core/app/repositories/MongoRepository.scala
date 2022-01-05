package repositories

import global.ApplicationResult
import global.Configurations.MONGO_DISPATCHER
import org.mongodb.scala.{MongoCollection, MongoDatabase}

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import org.mongodb.scala

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._

@Singleton
class MongoRepository @Inject()(mongoDatabase: MongoDatabase)(implicit @Named(MONGO_DISPATCHER) ec: ExecutionContext) {

  def test(): ApplicationResult[MongoCollection[scala.Document]] =
    Future {
      mongoDatabase.getCollection(ALERT_TYPE_COLLECTION)
    }.map { result =>
      // FIXME issue with observables
      Right(result)
    }

}

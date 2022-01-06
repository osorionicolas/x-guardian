package repositories

import global.ApplicationResult
import global.Configurations.MONGO_DISPATCHER
import models.AlertType
import utils.mongo.MongoDocumentDecoderInstances._

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class AlertTypeRepository @Inject()(mongoRepository: MongoRepository)(
  implicit @Named(MONGO_DISPATCHER) executionContext: ExecutionContext
) {
  def getAll(): ApplicationResult[Seq[AlertType]] =
    mongoRepository.getCollection(ALERT_TYPE_COLLECTION)
}

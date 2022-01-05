package repositories

import global.ApplicationResult
import models.AlertType
import utils.mongo.MongoDocumentDecoderInstances._

import javax.inject.{Inject, Singleton}

@Singleton
class AlertTypeRepository @Inject()(mongoRepository: MongoRepository) {

  def getAll(): ApplicationResult[Seq[AlertType]] =
    mongoRepository.getCollection[AlertType](ALERT_TYPE_COLLECTION)
}

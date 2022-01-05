package repositories

import global.ApplicationResult
import models.AlertType

import javax.inject.{Inject, Singleton}
import io.circe.syntax._
import utils.circe.CirceImplicits

@Singleton
class AlertTypeRepository @Inject()(mongoRepository: MongoRepository) {

  def getAll(): ApplicationResult[Seq[AlertType]] =
    mongoRepository.getCollection[AlertType](ALERT_TYPE_COLLECTION)
}

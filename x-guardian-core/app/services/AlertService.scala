package services

import akka.Done
import global.ApplicationResult
import models.{Alert, CreateAlert, Location}
import repositories.{ALERT_COLLECTION, MongoRepository}
import utils.MapMarkerContext
import utils.mongo.MongoDocumentEncoderInstances._

import javax.inject.{Inject, Singleton}

@Singleton
class AlertService @Inject()(mongoRepository: MongoRepository) {

  def create(
    alert: CreateAlert
  )(implicit mmc: MapMarkerContext): ApplicationResult[Done] =
    mongoRepository.insert[CreateAlert](alert, ALERT_COLLECTION)

  // TODO
  def emergencies(location: Location)(implicit mmc: MapMarkerContext): ApplicationResult[Seq[Alert]] = ???
}

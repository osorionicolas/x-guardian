package services

import global.ApplicationResult
import models.{Alert, CreateAlert}
import repositories.MongoRepository
import utils.MapMarkerContext

import javax.inject.{Inject, Singleton}

@Singleton
class AlertService @Inject()(mongoRepository: MongoRepository) {

  // TODO
  def create(alert: CreateAlert)(implicit mmc: MapMarkerContext): ApplicationResult[Alert] = ???
}

package repositories

import global.ApplicationResult
import models.AlertType

import javax.inject.{Inject, Singleton}

@Singleton
class AlertTypeRepository @Inject()(mongoRepository: MongoRepository) {

  def getAll(): ApplicationResult[List[AlertType]] = ???
}

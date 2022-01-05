package services

import global.ApplicationResult
import models.AlertType
import repositories.AlertTypeRepository

import javax.inject.{Inject, Singleton}

@Singleton
class AlertTypeService @Inject()(alertTypeRepository: AlertTypeRepository) {

  def getAll(): ApplicationResult[Seq[AlertType]] = alertTypeRepository.getAll()
}

package services

import akka.Done
import global.ApplicationResult
import repositories.MongoRepository

import javax.inject.{Inject, Singleton}

@Singleton
class AlertService @Inject()(mongoRepository: MongoRepository) {

  def create(alert: Alert): ApplicationResult[Done] = ???
}

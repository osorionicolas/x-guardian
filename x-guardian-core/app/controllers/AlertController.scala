package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.AlertService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AlertController @Inject()(val controllerComponents: ControllerComponents, alertService: AlertService)(
  implicit ec: ExecutionContext
) extends BaseController {

  // TODO
  def create() = Action.async(_ => Future(Ok))

  // TODO
  def getNearest(location: String): Action[AnyContent] = ???
}

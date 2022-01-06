package controllers

import controllers.helpers.{Decodable, ErrorToResultConverter}
import io.circe.syntax._
import play.api.Logging
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.AlertTypeService

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class AlertTypeController @Inject()(val controllerComponents: ControllerComponents, alertTypeService: AlertTypeService)(
  implicit ec: ExecutionContext
) extends BaseController
    with ErrorToResultConverter
    with Decodable
    with Logging {

  def getAll(): Action[AnyContent] = Action.async { _ =>
    alertTypeService.getAll().map {
      case Right(alertTypes) => Ok(alertTypes.asJson)
      case Left(error) =>
        handleApplicationError(error)
    }
  }
}

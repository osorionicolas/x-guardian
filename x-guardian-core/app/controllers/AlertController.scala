package controllers

import controllers.helpers.Decodable
import io.circe.syntax._
import models.CreateAlertDTO
import models.converters.CreateAlertDTOOps._
import play.api.Logging
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.AlertService
import utils.MapMarkerContext._

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.{Map => MMap}
import scala.concurrent.ExecutionContext

@Singleton
class AlertController @Inject()(val controllerComponents: ControllerComponents, alertService: AlertService)(
  implicit ec: ExecutionContext
) extends BaseController
    with Decodable
    with Logging {

  def create() = Action.async(decode[CreateAlertDTO]) { implicit request =>
    implicit val mmc = fromRequest(MMap(USER_ID -> request.body.userId))
    alertService.create(request.body.toDomain()).map {
      case Right(alert) =>
        logger.info(s"Alert created successfully: $alert")
        Created(alert.asJson)
    }
  }

  // TODO
  def getNearest(location: String): Action[AnyContent] = ???
}

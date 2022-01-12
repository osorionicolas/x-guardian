package controllers

import controllers.helpers.{Decodable, ErrorToResultConverter}
import models.CreateAlertDTO
import models.converters.CreateAlertDTOOps._
import play.api.Logging
import play.api.mvc.{BaseController, ControllerComponents}
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
    with Logging
    with ErrorToResultConverter {

  def create() = Action.async(decode[CreateAlertDTO]) { implicit request =>
    implicit val mmc = fromRequest(MMap(USER_ID -> request.body.userId))
    logger.info("Registering alert")
    alertService.create(request.body.toDomain()).map {
      case Left(error) =>
        handleApplicationError(error)
      case Right(alert) =>
        logger.info(s"Alert created successfully: $alert")
        Created
    }
  }
}

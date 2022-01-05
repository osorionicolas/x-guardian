package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class HealthcheckController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def check() = Action(Ok("Up and Running"))
}

package controllers

import play.api.mvc.{BaseController, ControllerComponents}
import repositories.MongoRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class TestController @Inject()(val controllerComponents: ControllerComponents, mongoRepository: MongoRepository)(
  implicit ec: ExecutionContext
) extends BaseController {

  def test() = Action.async {
    mongoRepository.test().map { result =>
      println(result)
      Ok
    }
  }
}

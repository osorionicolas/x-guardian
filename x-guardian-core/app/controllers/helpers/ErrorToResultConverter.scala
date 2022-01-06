package controllers.helpers

import io.circe.JsonObject
import io.circe.generic.extras.AutoDerivation
import io.circe.syntax._
import models.errors._
import play.api.mvc.{Result, Results}
import utils.circe.CirceImplicits

/**
  * Trait containing a conversion helper methods to map [[ApplicationError]] into [[Result]]
  *
  */
trait ErrorToResultConverter extends CirceImplicits with AutoDerivation with Results {
  _: Decodable =>

  /**
    * Default error handler logs a message [[String]] and then delegates error handling/mapping
    * to [[ToResultConverter]]
    *
    * @param msg the message to be logged
    * @return
    */
  def defaultErrorHandlerWithMessage(msg: String): ApplicationError => Result =
    error => {
      logger.info(msg)
      handleApplicationError(error)
    }

  /**
    * Converts an [[ApplicationError]] to the best matching Play framework Result for it.
    *
    * @param error Specific application error to convert.
    * @return      Proper Play framework Result that should be returned to the requesting HTTP client according to the
    *              and content of the given error.
    */
  def handleApplicationError(error: ApplicationError): Result =
    error match {
      case authError: AuthenticationError   => Unauthorized(authError.asJson)
      case ClientError(_)                   => BadRequest(JsonObject.empty.asJson)
      case EmptyResponse                    => InternalServerError
      case NotFoundResponse                 => NotFound
      case badRequestError: BadRequestError => BadRequest(badRequestError.asJson)
      case _                                => InternalServerError
    }
}

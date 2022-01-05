package models.errors.validations

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import utils.circe.CirceImplicits

/**
  * Error representation for each field.
  *
  * @param code    Error type.
  * @param param   Property with the error.
  */
case class ValidationError(code: String, param: String)

object ValidationError extends CirceImplicits {
  implicit val decoder: Decoder[ValidationError] = deriveDecoder[ValidationError]
  implicit val encoder: Encoder[ValidationError] = deriveEncoder[ValidationError]
}

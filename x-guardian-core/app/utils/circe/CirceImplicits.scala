package utils.circe

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import io.circe.{Decoder, Encoder, Printer}
import models._
import utils.dateutils.dateTimeFormatter

import java.time.LocalDateTime
import scala.util.{Failure, Success, Try}

trait CirceImplicits {
  implicit val customPrinter: Printer      = Printer.noSpaces.copy(dropNullValues = true)
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val localDateTimeEncoder: Encoder[LocalDateTime] =
    Encoder.encodeString.contramap[LocalDateTime](_.format(dateTimeFormatter))

  implicit val localDateDecoder: Decoder[LocalDateTime] = Decoder.decodeString.emap[LocalDateTime](str => {
    Try(LocalDateTime.parse(str, dateTimeFormatter)) match {
      case Failure(exception) => Left(exception.getMessage)
      case Success(value)     => Right(value)
    }
  })

  implicit val coordsEncoder = deriveConfiguredEncoder[Coords]
  implicit val coordsDecoder = deriveConfiguredDecoder[Coords]

  implicit val locationEncoder = deriveConfiguredEncoder[Location]
  implicit val locationDecoder = deriveConfiguredDecoder[Location]

  implicit val alertTypeEncoder = deriveConfiguredEncoder[AlertType]
  implicit val alertTypeDecoder = deriveConfiguredDecoder[AlertType]

  implicit val alertEncoder = deriveConfiguredEncoder[Alert]
  implicit val alertDecoder = deriveConfiguredDecoder[Alert]

  implicit val createAlertEncoder = deriveConfiguredEncoder[CreateAlert]
  implicit val createAlertDecoder = deriveConfiguredDecoder[CreateAlert]

  implicit val createAlertDtoEncoder = deriveConfiguredEncoder[CreateAlertDTO]
  implicit val createAlertDtoDecoder = deriveConfiguredDecoder[CreateAlertDTO]
}

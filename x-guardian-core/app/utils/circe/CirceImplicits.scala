package utils.circe

import io.circe.Printer
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models._

trait CirceImplicits {
  implicit val customPrinter: Printer      = Printer.noSpaces.copy(dropNullValues = true)
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  // TODO localdatetime enc/dec

  implicit val alertTypeEncoder = deriveConfiguredEncoder[AlertType]
  implicit val alertTypeDecoder = deriveConfiguredDecoder[AlertType]

  implicit val alertEncoder = deriveConfiguredEncoder[Alert]
  implicit val alertDecoder = deriveConfiguredDecoder[Alert]

  implicit val createAlertEncoder = deriveConfiguredEncoder[CreateAlert]
  implicit val createAlertDecoder = deriveConfiguredDecoder[CreateAlert]

  implicit val coordsEncoder = deriveConfiguredEncoder[Coords]
  implicit val coordsDecoder = deriveConfiguredDecoder[Coords]

  implicit val locationEncoder = deriveConfiguredEncoder[Location]
  implicit val locationDecoder = deriveConfiguredDecoder[Location]
}

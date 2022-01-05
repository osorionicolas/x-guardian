package utils.circe

import io.circe.Printer
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.AlertType

trait CirceImplicits {
  implicit val customPrinter: Printer      = Printer.noSpaces.copy(dropNullValues = true)
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val alertTypeEncoder = deriveConfiguredEncoder[AlertType]
  implicit val alertTypeDecoder = deriveConfiguredDecoder[AlertType]
}

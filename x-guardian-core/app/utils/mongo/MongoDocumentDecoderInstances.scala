package utils.mongo

import models.AlertType
import models.errors.{ApplicationError, ParsingError}
import utils.circe.CirceImplicits
import utils.mongo.MongoDocumentDecoder.{MongoDocumentDecoder, instance}

object MongoDocumentDecoderInstances extends CirceImplicits {

  val alertTypeInstance: MongoDocumentDecoder[AlertType] = instance[AlertType] { doc =>
    val result = for {
      id   <- doc.get("_id").map(_.toString)
      name <- doc.get("name").map(_.toString)
    } yield AlertType(id, name)

    // TODO improve error parsing message (maybe add either instead of option)
    result.fold[Either[ApplicationError, AlertType]](Left(ParsingError("error")))(entity => Right(entity))
  }
}

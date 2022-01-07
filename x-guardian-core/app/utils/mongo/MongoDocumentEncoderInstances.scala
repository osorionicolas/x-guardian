package utils.mongo

import models.{AlertType, CreateAlert}
import org.mongodb.scala.Document
import utils.dateutils.dateTimeFormatter
import utils.mongo.MongoDocumentEncoder.{MongoDocumentEncoder, instance}

object MongoDocumentEncoderInstances {

  implicit val alertTypeDocEncoderInstance: MongoDocumentEncoder[AlertType] = instance[AlertType] { alertType =>
    Document("name" -> alertType.name)
  }

  implicit val createAlertDocEncoderInstance: MongoDocumentEncoder[CreateAlert] = instance[CreateAlert] { createAlert =>
    Document(
      "alert" -> Document(
        "id"   -> createAlert.alert.id,
        "name" -> createAlert.alert.name
      ),
      "user_id"    -> createAlert.userId,
      "created_at" -> createAlert.createdAt.format(dateTimeFormatter),
      "location" -> Document(
        "latitude"  -> createAlert.location.latitude,
        "longitude" -> createAlert.location.longitude
      )
    )
  }
}

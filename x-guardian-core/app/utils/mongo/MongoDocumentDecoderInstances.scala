package utils.mongo

import models.AlertType
import utils.circe.CirceImplicits
import utils.mongo.MongoDocumentDecoder.{MongoDocumentDecoder, instance}
import utils.mongo.MongoTypesConverter.{IntFieldType, ObjectIdFieldType, StringFieldType, extractFieldTmp}

object MongoDocumentDecoderInstances extends CirceImplicits {

  implicit val alertTypeInstance: MongoDocumentDecoder[AlertType] = instance[AlertType] { docFieldMap =>
    for {
      id           <- extractFieldTmp[String](docFieldMap, ALERT_TYPE_ID_FIELD, ObjectIdFieldType)
      name         <- extractFieldTmp[String](docFieldMap, ALERT_TYPE_NAME_FIELD, StringFieldType)
      basePriority <- extractFieldTmp[Int](docFieldMap, ALERT_TYPE_BASE_PRIORITY_FIELD, IntFieldType)
      icon         <- extractFieldTmp[String](docFieldMap, ALERT_TYPE_ICON_FIELD, StringFieldType)
    } yield AlertType(id, name, basePriority, icon)
  }
}

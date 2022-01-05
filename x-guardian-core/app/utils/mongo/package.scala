package utils

import org.mongodb.scala.bson

package object mongo {

  type DocFieldMap = Map[String, bson.BsonValue]

  // alert_type fields
  val ALERT_TYPE_ID_FIELD   = "_id"
  val ALERT_TYPE_NAME_FIELD = "name"
}

package utils

import org.mongodb.scala.bson

package object mongo {

  type DocFieldMap = Map[String, bson.BsonValue]
}

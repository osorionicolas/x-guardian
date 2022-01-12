package utils.mongo

import global.EitherResult
import models.errors.ParsingError
import org.mongodb.scala.bson

import scala.reflect._

object MongoTypesConverter {

  // not used at the moment
  // FIXME
  def extractField[T](docFieldMap: DocFieldMap, field: String): EitherResult[T] =
    docFieldMap.get(field) match {
      case Some(value) => mongoToScalaUnwrapper(value)
      case None        => Left(ParsingError(field))
    }

  // not used at the moment
  // FIXME
  def mongoToScalaUnwrapper[T](bsonValue: bson.BsonValue)(implicit classTag: ClassTag[T]): EitherResult[T] =
    classTag.runtimeClass match {
      case clazz if clazz == classOf[Int]     => Right(bsonValue.asInt32().getValue.asInstanceOf[T])
      case clazz if clazz == classOf[String]  => Right(bsonValue.asString().getValue.asInstanceOf[T])
      case clazz if clazz == classOf[Boolean] => Right(bsonValue.asBoolean().getValue.asInstanceOf[T])
    }

  sealed trait FieldType
  case object ObjectIdFieldType extends FieldType
  case object StringFieldType   extends FieldType
  case object BooleanFieldType  extends FieldType
  case object IntFieldType      extends FieldType

  /**
    * Extract a field from a mongo Document and convert it to Scala type.
    *
    * `IMPORTANT`: this is a temporal solution until fix generic errors with `scala.universal`
    */
  def extractFieldTmp[T](docFieldMap: DocFieldMap, field: String, fieldType: FieldType): EitherResult[T] = {
    def toEitherResult[T](option: Option[T], field: String): EitherResult[T] =
      option match {
        case Some(value) => Right(value)
        case None        => Left(ParsingError(field))
      }

    val maybeBsonValue = docFieldMap.get(field)

    val maybeConvertedValue = fieldType match {
      case ObjectIdFieldType =>
        maybeBsonValue.map(_.asObjectId().getValue.toString)
      case StringFieldType =>
        maybeBsonValue.map(_.asString().getValue)
      case BooleanFieldType =>
        maybeBsonValue.map(_.asBoolean().getValue)
      case IntFieldType =>
        maybeBsonValue.map(_.asInt32().getValue)
    }

    toEitherResult(maybeConvertedValue, field).asInstanceOf[EitherResult[T]]
  }
}

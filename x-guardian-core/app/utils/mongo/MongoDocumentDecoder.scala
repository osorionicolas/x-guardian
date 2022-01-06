package utils.mongo

import io.circe.Decoder
import io.circe.parser.{decode => circeDecode}
import models.errors.{ApplicationError, ParsingError}
import org.mongodb.scala.Document

object MongoDocumentDecoder {

  // typeclass definition
  trait MongoDocumentDecoder[T] {
    def decode(doc: Document): Either[ApplicationError, T]
  }

  // API
  def parse[T: MongoDocumentDecoder](doc: Document): Either[ApplicationError, T] =
    implicitly[MongoDocumentDecoder[T]].decode(doc)

  // typeclass instance factory
  def instance[T](mapper: DocFieldMap => Either[ApplicationError, T]): MongoDocumentDecoder[T] =
    new MongoDocumentDecoder[T] {
      override def decode(doc: Document): Either[ApplicationError, T] =
        mapper(doc.toMap)
    }
}

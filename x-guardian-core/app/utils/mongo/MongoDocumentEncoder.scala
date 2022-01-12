package utils.mongo

import org.mongodb.scala.Document

object MongoDocumentEncoder {

  // typeclass definition
  trait MongoDocumentEncoder[T] {
    def encode(entity: T): Document
  }

  // API
  def toDocument[T: MongoDocumentEncoder](entity: T): Document =
    implicitly[MongoDocumentEncoder[T]].encode(entity)

  // typeclass instance factory
  def instance[T](mapper: T => Document): MongoDocumentEncoder[T] =
    new MongoDocumentEncoder[T] {
      override def encode(entity: T): Document = mapper(entity)
    }
}

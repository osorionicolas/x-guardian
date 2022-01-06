package utils.mongo

import org.mongodb.scala.Document
import utils.mongo.MongoDocumentEncoder.MongoDocumentEncoder

object MongoDocumentEncoderSyntax {

  implicit class MongoDocumentEncoderOps[T](entity: T) {
    def asDocument(implicit mongoDocEncoder: MongoDocumentEncoder[T]): Document =
      mongoDocEncoder.encode(entity)

  }
}

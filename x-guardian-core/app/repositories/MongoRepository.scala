package repositories

import org.mongodb.scala.MongoDatabase

import javax.inject.{Inject, Singleton}

@Singleton
class MongoRepository @Inject()(mongoDatabase: MongoDatabase) {

  def test() =
    ()
}

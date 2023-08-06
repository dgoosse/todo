package services

import models.Todo
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.ObservableImplicits
import org.mongodb.scala.model.Filters._

import scala.concurrent.{ExecutionContext, Future}

class MongoRepository extends AsyncRepository[Todo] {

  val codecRegistry = fromRegistries(fromProviders(classOf[Todo]), DEFAULT_CODEC_REGISTRY )

  val mongoClient: MongoClient = MongoClient()

  val database: MongoDatabase = mongoClient.getDatabase("goo").withCodecRegistry(codecRegistry)

  val collection: MongoCollection[Todo] = database.getCollection("todos")

  def create(id: String, value: Todo)(implicit ec: ExecutionContext): Future[Option[Todo]] = {
    collection
      .insertOne(value)
      .toFuture()
      .map(i => if (i.wasAcknowledged()) Option(value) else None)
  }

  def read(id: String)(implicit ec: ExecutionContext): Future[Option[Todo]] = {
    collection
      .find(equal("_id", id))
      .toFuture()
      .map(_.headOption)
  }

  def readAll()(implicit ec: ExecutionContext): Future[Seq[Todo]] = {
    collection
      .find()
      .toFuture()
  }

  def deleteAll()(implicit ec: ExecutionContext): Future[Boolean] = {
    collection
      .deleteMany(empty())
      .toFuture()
      .map(_.wasAcknowledged())
  }

  def delete(id: String)(implicit ec: ExecutionContext): Future[Boolean] = {
    collection
      .deleteOne(equal("_id", id))
      .toFuture()
      .map(_.wasAcknowledged())
  }

  def update(id: String, value: Todo)(implicit ec: ExecutionContext): Future[Option[Todo]] = {
    collection
      .replaceOne(equal("_id", id), value)
      .toFuture()
      .map(i => if (i.wasAcknowledged()) Option(value) else None)
  }
}

object MongoRepository {
  def apply(): MongoRepository = new MongoRepository()
}

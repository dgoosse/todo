package models

import play.api.libs.json._
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.ObjectId

case class Todo(_id: Option[String]=None, title: Option[String], order: Option[Int]=None, completed: Option[Boolean]=None, url: Option[String]=None)

object Todo {
  implicit val format: OFormat[Todo] = Json.format[Todo]
}

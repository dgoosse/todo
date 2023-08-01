package models

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

case class Todo(title: String, id: Option[String]=Some(java.util.UUID.randomUUID.toString), order: Option[Int]=Some(0), completed: Option[Boolean]=Some(false), url: Option[String]=Some("/"))

object Todo {
  implicit val format: OFormat[Todo] = Json.format[Todo]

  /*
  implicit val todoReads: Reads[Todo] = (
    (JsPath \ "title").read[String] and
      (JsPath \ "completed").readWithDefault[Boolean](false) and
      (JsPath \ "url").readNullable[String]
    )(Todo.apply _)

  implicit val todoWrites: Writes[Todo] = (
    (JsPath \ "title").write[String] and
      (JsPath \ "completed").write[Boolean] and
      (JsPath \ "url").writeNullable[String]
    )(unlift(Todo.unapply))
  */

}

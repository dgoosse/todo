package models

import play.api.libs.json._

case class Todo(_id: Option[String]=None, title: Option[String], order: Option[Int]=None, completed: Option[Boolean]=None, url: Option[String]=None)

object Todo {
  implicit val format: OFormat[Todo] = Json.format[Todo]

  def mergeWith(toDo: Todo, inTodo: Todo):Todo ={
    toDo.copy(
      title = inTodo.title.orElse(toDo.title),
      order = inTodo.order.orElse(toDo.order),
      completed = inTodo.completed.orElse(toDo.completed)
    )
  }
}

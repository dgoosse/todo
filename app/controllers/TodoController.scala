package controllers

import models.Todo
import play.api.libs.json.Json
import play.api.mvc._
import services.SimpleRepository

import javax.inject._

@Singleton
class TodoController @Inject()(cc: ControllerComponents, repository: SimpleRepository) extends AbstractController(cc) {

  //def uuid() = java.util.UUID.randomUUID.toString

  // GET /api/todos
  def get = Action {
    val all = repository.all()
    Ok(Json.toJson(all))
  }

  // POST /api/todos
  def post = Action.apply(parse.json[Todo]) { r =>
    val uuid = java.util.UUID.randomUUID.toString
    val newTodo = r.body.copy(id = Some(uuid), url = Some(s"http://localhost:9000/api/todos/$uuid"))
    Ok(Json.toJson(repository.add(newTodo)))
  }

}

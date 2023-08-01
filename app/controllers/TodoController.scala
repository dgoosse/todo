package controllers

import models.Todo
import play.api.libs.json.Json
import play.api.mvc._
import services.SimpleRepository

import javax.inject._

@Singleton
class TodoController @Inject()(cc: ControllerComponents, repository: SimpleRepository) extends AbstractController(cc) {

  repository.add(Todo("first todo"))

  // GET /api/todos
  def get = Action {
    val all = repository.all()
    Ok(Json.toJson(all))
  }

  def post = Action.apply(parse.json[Todo]) { r =>
    val newTodo = r.body.copy(url = Some("http://localhost:9000/api/todos/1"))
    repository.add(newTodo)
    Ok(Json.toJson(newTodo))
  }

}

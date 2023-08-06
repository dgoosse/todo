package controllers

import models.Todo
import play.api.libs.json.Json
import play.api.mvc._
import services._

import javax.inject._
import org.mongodb.scala.ObservableImplicits
import org.mongodb.scala.bson.ObjectId

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TodoController @Inject()(cc: ControllerComponents, repository: MongoRepository)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  // GET /api/todos
  def get = Action.async {
    repository.readAll().map(r => Ok(Json.toJson(r)))
  }

  // GET /api/todos/:id
  def getById(id: String) = Action.async {
    repository.read(id).map(toDo => if (toDo.isDefined) Ok(Json.toJson(toDo)) else NotFound)
  }

  // POST /api/todos
  def post = Action.apply(parse.json[Todo]).async { r =>
    val inTodo = r.body
    val id: ObjectId = new ObjectId()
    val newTodo = r.body.copy(_id = Some(id.toString), order = inTodo.order.orElse(Some(0)), completed = inTodo.completed.orElse(Some(false)), url = Some(s"http://localhost:9000/api/todos/$id"))
    repository.create(id.toString, newTodo).map(toDo => if (toDo.isDefined) Ok(Json.toJson(toDo)) else NotFound)
  }

  // DELETE /api/todos
  def delete = Action.async {
    repository.deleteAll().map(r => Ok(Json.toJson(r)))
  }

  // DELETE /api/todos/:id
  def deleteById(id: String) = Action.async {
    repository.delete(id).map(done => if (done) Ok(Json.toJson(done)) else NotFound)
  }

  // PATCH /api/todos/:id
  def patchById(id: String) = Action.apply(parse.json[Todo]).async { r =>
    val inTodo = r.body
    repository
      .read(id)
      .flatMap(toDo => repository.update(id, Todo.mergeWith(toDo.get, inTodo)))
      .map(toDo => if (toDo.isDefined) Ok(Json.toJson(toDo)) else NotFound)
  }

}

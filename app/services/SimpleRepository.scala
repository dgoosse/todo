package services

import models.Todo

class SimpleRepository extends Repository[Todo] {

  var db = collection.mutable.Map[String, Todo]()

  def create(id: String, toDo: Todo): Option[Todo] = {
    val copyTodo = toDo
    db.put(id, copyTodo)
    Some(copyTodo)
  }

  def read(key: String): Option[Todo] = {
    db.get(key)
  }

  def readAll(): List[Todo] = {
    db.values.toList
  }

  def deleteAll(): Unit = {
    db.clear()
  }

  def delete(id: String): Unit = {
    db.remove(id)
  }

  def update(id: String, value: Todo): Option[Todo] = {
    db.put(id, value)
  }

  override def toString(): String = db.toString()
}

object SimpleRepository {
  def apply(): SimpleRepository = new SimpleRepository()
}

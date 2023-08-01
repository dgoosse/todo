package services

import models.Todo

trait Repository[T] {
  def add(key: String, value: T): Option[T]
  def get(key: String): Option[T]
  def all(): List[T]
  def deleteAll(): Unit
  def delete(id: String): Unit
  def update(id: String, value: T): Option[T]
}

class SimpleRepository extends Repository[Todo] {

  var db = collection.mutable.Map[String, Todo]()

  def add(id: String, toDo: Todo): Option[Todo] = {
    val copyTodo = toDo
    db.put(id, copyTodo)
    Some(copyTodo)
  }

  def add(toDo: Todo): Option[Todo] = {
    add(toDo.id.getOrElse("0"), toDo)
  }

  def get(key: String): Option[Todo] = {
    db.get(key)
  }

  def all(): List[Todo] = {
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

package services

trait Repository[A] {
  def create(id: String, value: A): Option[A]
  def read(id: String): Option[A]
  def update(id: String, value: A): Option[A]
  def delete(id: String): Unit
  def readAll(): List[A]
  def deleteAll(): Unit
}
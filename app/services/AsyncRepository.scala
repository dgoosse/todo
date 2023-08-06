package services

import scala.concurrent._

trait AsyncRepository[A] {
  def create(id: String, value: A)(implicit ec: ExecutionContext): Future[Option[A]]
  def read(id: String)(implicit ec: ExecutionContext): Future[Option[A]]
  def update(id: String, value: A)(implicit ec: ExecutionContext): Future[Option[A]]
  def delete(id: String)(implicit ec: ExecutionContext): Future[Boolean]
  def readAll()(implicit ec: ExecutionContext): Future[Seq[A]]
  def deleteAll()(implicit ec: ExecutionContext): Future[Boolean]
}
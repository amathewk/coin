package repo

import javax.inject.Inject
import org.mongodb.scala.{Completed, Document, FindObservable, SingleObservable}

import scala.concurrent.ExecutionContext
import scala.concurrent._
import ExecutionContext.Implicits.global

class TransactionRepo @Inject() (coinMongoClient: CoinMongoClient) {

  def save(name: String, x: String, y: String)(implicit ec: ExecutionContext) = {
    val coll = coinMongoClient.collection
    val doc: Document = Document("name" -> name, "info" -> Document("x" -> x, "y" -> y))

    val insertObservable: SingleObservable[Completed] = coll.insertOne(doc)

    insertObservable.toFuture
  }

  def transactions()(implicit ec: ExecutionContext) = {
    coinMongoClient.collection.find().toFuture
  }

}
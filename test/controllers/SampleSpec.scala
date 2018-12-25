package controllers

import org.mongodb.scala.model.Filters._
import org.scalatest.FunSuite

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Updates._
import Helpers._

class HelloWorldSpec extends FunSuite {

  test("temp") {
    import org.mongodb.scala._

    // To directly connect to the default server localhost on port 27017
//    val mongoClient: MongoClient = MongoClient()


//    // or provide custom MongoClientSettings
//    val settings: MongoClientSettings = MongoClientSettings.builder()
//      .applyToClusterSettings(b => b.hosts(List(new ServerAddress("172.18.0.3")).asJava).
//      .build()

//    val mongoClient: MongoClient = MongoClient(settings)




    import com.mongodb.MongoCredential._

    // ...

    val user: String = "root"                       // the user name
    val source: String = "admin"                 // the source where the user is defined
    val password: Array[Char] = "example".toCharArray  // the password as a character array
    // ...
    val credential: MongoCredential = createCredential(user, source, password)

    val settings: MongoClientSettings = MongoClientSettings.builder()
//      .
//      .applyToClusterSettings(b => b.hosts(List(new ServerAddress("mongodb://172.30.0.1:27017/coin")).asJava))
      .applyToClusterSettings(b => b.hosts(List(new ServerAddress("172.30.0.1:27017")).asJava))
        .credential(credential)
        .build()
//    val mongoClient: MongoClient = MongoClient(settings)

    // Use a Connection String
//    val mongoClient: MongoClient = MongoClient("mongodb://172.20.0.1:27017/coin")
    val mongoClient: MongoClient = MongoClient(settings)

    val database: MongoDatabase = mongoClient.getDatabase("coin")

    println(database.name)

    val collection: MongoCollection[Document] = database.getCollection("testColl")

    collection.drop()

    val deleteObs = collection.deleteMany(equal("name", "MongoDB"))
//    collection.find(equal("name", "MongoDB")).first().printHeadResult()

    val doc: Document = Document("name" -> "name1", "info" -> Document("x" -> 203, "y" -> 102))

    val insertObservable: SingleObservable[Completed] = collection.insertOne(doc)

    val countObservable = collection.countDocuments()
//    countObservable.subscribe((count: Long) => println(count))

    val count = for {
      deleteResult <- deleteObs;
      insertResult <- insertObservable;
      countResult <- countObservable
    } yield countResult

    count.printHeadResult()

//    Await.result(insertObservable.toFuture, Duration.Inf)
//    Await.result(countObservable.toFuture, Duration.Inf)

    mongoClient.close()

    true
  }

}

//    val observer = new Observer[Completed] {
//      override def onNext(result: Completed): Unit = println(s"onNext: $result")
//
//      override def onError(e: Throwable): Unit = println(s"onError: $e")
//
//      override def onComplete(): Unit = println("onComplete")
//    }
//
//    insertObservable.subscribe(observer)


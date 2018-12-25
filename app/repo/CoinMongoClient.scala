package repo

import org.mongodb.scala._
import com.mongodb.MongoCredential._
import javax.inject.Inject

import scala.collection.JavaConverters._

class CoinMongoClient @Inject() () {

  private val user: String = "root"                       // the user name
  private val source: String = "admin"                 // the source where the user is defined
  private val password: Array[Char] = "example".toCharArray  // the password as a character array

  // ...
  private val credential: MongoCredential = createCredential(user, source, password)

  private val settings: MongoClientSettings = MongoClientSettings.builder()
    //      .
    //      .applyToClusterSettings(b => b.hosts(List(new ServerAddress("mongodb://172.30.0.1:27017/coin")).asJava))
    .applyToClusterSettings(b => b.hosts(List(new ServerAddress("localhost:27017")).asJava))
    .credential(credential)
    .build()
  //    val mongoClient: MongoClient = MongoClient(settings)

  // Use a Connection String
  //    val mongoClient: MongoClient = MongoClient("mongodb://172.20.0.1:27017/coin")
  val mongoClient: MongoClient = MongoClient(settings)

  val database: MongoDatabase = mongoClient.getDatabase("coin")

  val collection: MongoCollection[Document] = database.getCollection("testColl")

}
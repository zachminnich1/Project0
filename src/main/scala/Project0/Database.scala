package Project0

import org.mongodb.scala.{MongoClient, MongoCollection, Observable}
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}

class Database {
  val codecRegistry = fromRegistries(fromProviders(classOf[TravelData]), MongoClient.DEFAULT_CODEC_REGISTRY)
  val client = MongoClient()
  val db = client.getDatabase("Travels").withCodecRegistry(codecRegistry)
  val collection : MongoCollection[TravelData] = db.getCollection("Travel")

  def getResults[T](obs: Observable[T]): Seq[T] = {
    Await.result(obs.toFuture(),Duration(10, SECONDS))
  }

  def printResults[T](obs: Observable[T]): Unit = {
    getResults(obs).foreach(println(_))
  }

  /** adds item to database */
  def addOne(name: String, pathLength: Int, pathing: List[String], finish: Boolean): Unit = {
    printResults(collection.insertOne(TravelData(name, pathLength, pathing, finish)))
  }

  def closeConnection(): Unit = client.close()
}

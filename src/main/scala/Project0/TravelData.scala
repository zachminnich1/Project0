package Project0

import org.bson.types.ObjectId

case class TravelData(_id: ObjectId, name: String, pathLength: Int, pathing: List[String], finish: Boolean) {}

object TravelData {
  def apply(name: String, pathLength: Int, pathing: List[String], finish: Boolean) :TravelData = TravelData(new ObjectId(), name, pathLength, pathing, finish)
}

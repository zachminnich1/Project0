package Project0

class Room(var roomID:String, var North: String, var East: String, var South: String, var West: String, var Description: String = "") {

  /** Prints room description */
  def printDesc():Unit = {
    println(Description)
  }

  /** Sets all values for the room */
  def setAll(valArray: Array[String]):Unit = {
    roomID = valArray(0)
    North = valArray(1)
    East = valArray(2)
    South = valArray(3)
    West = valArray(4)
    Description = valArray(5)
  }

  /** Sets all values for the room other than ID and description */
  def setDir(nRoom: String, eRoom: String, sRoom: String, wRoom: String):Unit = {
    North = nRoom
    East = eRoom
    South = sRoom
    West = wRoom
  }

  /** Sets the North value of the Room */
  def setNorth(nRoom: String):Unit = {
    North = nRoom
  }

  /** Sets the East value of the Room */
  def setEast(eRoom: String):Unit = {
    East = eRoom
  }

  /** Sets the South value of the Room */
  def setSouth(sRoom: String):Unit = {
    South = sRoom
  }

  /** Sets the West value of the Room */
  def setWest(wRoom: String):Unit = {
    West = wRoom
  }

  /** Returns the Room ID of the Room */
  def getRoomID():String = {
    roomID
  }

  /** Returns the North value of the Room */
  def getNorth():String = {
    North
  }

  /** Returns the East value of the Room */
  def getEast():String = {
    East
  }

  /** Returns the South value of the Room */
  def getSouth():String = {
    South
  }

  /** Returns the West value of the Room */
  def getWest():String = {
    West
  }
}

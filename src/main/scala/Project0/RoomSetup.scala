package Project0

class RoomSetup {

  /** Creates all the rooms that will be traversed */
  def setRooms():List[Room] = {

    //creates all the empty rooms
    val R00 = new Room("R00", null, null, null, null, null)
    val R10 = new Room("R10", null, null, null, null, null)
    val R20 = new Room("R20", null, null, null, null, null)
    val R30 = new Room("R30", null, null, null, null, null)
    val R01 = new Room("R01", null, null, null, null, null)
    val R11 = new Room("R11", null, null, null, null, null)
    val R21 = new Room("R21", null, null, null, null, null)
    val R31 = new Room("R31", null, null, null, null, null)
    val R02 = new Room("R02", null, null, null, null, null)
    val R12 = new Room("R12", null, null, null, null, null)
    val R22 = new Room("R22", null, null, null, null, null)
    val R32 = new Room("R32", null, null, null, null, null)
    val R03 = new Room("R03", null, null, null, null, null)
    val R13 = new Room("R13", null, null, null, null, null)
    val R23 = new Room("R23", null, null, null, null, null)
    val R33 = new Room("R33", null, null, null, null, null)

    //puts all rooms in a list
    val rList: List[Room] = List(R00,R10,R20,R30,R01,R11,R21,R31,R02,R12,R22,R32,R03,R13,R23,R33)
    rList
  }
}

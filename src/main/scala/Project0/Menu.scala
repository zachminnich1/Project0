package Project0

import java.io.FileNotFoundException

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
import scala.util.matching.Regex

class Menu() {

  //sets up the empty rooms
  val RS = new RoomSetup
  val roomList: List[Room] = RS.setRooms()

  val commandArgPattern : Regex = "(\\w+)\\s*(.*)".r

  val db = new Database

  /** Welcome message */
  def printWelcome():String = {
    println("Please enter your name: ")
    val name = StdIn.readLine()
    println(s"Welcome to the Game, $name")
    name
  }

  /** Asks for file and then sets up maze */
  def getFile():Unit = {
    var trying = true

    while (trying) {
      try {
        println("Please enter the filename of the maze: ")
        val maze = StdIn.readLine()

        //populates the roomList with data from file
        val imported = new ImportFile
        imported.csv(maze, roomList)
        trying = false
      }
      catch {
        case fnf: FileNotFoundException => println("Invalid File name, please try again")
      }
    }
  }

  /** Will tell you how many times you have changed rooms */
  def printMoves(move: Int):Unit = {
    println(s"You have taken $move moves")
  }

  /** Prints options of what to do */
  def printOptions():Unit = {
    println("You can MOVE to the NORTH, EAST, SOUTH, or WEST")
    println("You can LOOK around the room")
    println("You can see how many MOVES you have made")
    println("You can see the PATH you have taken")
    println("You can EXIT the game")
  }

  /** Prompts the user for their choices and matches them */
  def menu():Unit = {
    var name = printWelcome()

    getFile()

    var continueMenuLoop = true

    //will track how many moves it takes you to finish
    var pathLength = 0

    val path = ArrayBuffer[String]()

    //the room that you are currently in
    var currentRoom = roomList.head;

    /** Gets the path they took and counts one more step taken */
    def move(direction: String):Unit = {
      pathLength += 1
      path += direction
    }

/** moves to the room to the North */
    def moveNorth(): Unit = {
      //goes through rooms to check which room to move to
      for(rooms <- roomList) if(rooms.getRoomID() == currentRoom.getNorth()) {
          currentRoom = rooms
          move("North")
          return
        }
      println("There is no room to the North!")
    }

    /** moves to the room to the East */
    def moveEast(): Unit = {
      //goes through rooms to check which room to move to
      for(rooms <- roomList) if(rooms.getRoomID() == currentRoom.getEast()) {
          currentRoom = rooms
          move("East")
          return
        }
      println("There is no room to the East!")
    }

    /** moves to the room to the South */
    def moveSouth(): Unit = {
      //goes through rooms to check which room to move to
      for(rooms <- roomList) if(rooms.getRoomID() == currentRoom.getSouth()) {
          currentRoom = rooms
          move("South")
          return
        }
      println("There is no room to the South!")
    }

    /** moves to the room to the West */
    def moveWest(): Unit = {

      //goes through rooms to check which room to move to
      for(rooms <- roomList) if(rooms.getRoomID() == currentRoom.getWest()) {
          currentRoom = rooms
          move("West")
          return
        }
      println("There is no room to the West!")
    }

    // This loop here will repeatedly prompt, listen, run code, and repeat
    while (continueMenuLoop) {
      currentRoom.printDesc()
      printOptions()
      // get user input with StdIn.readLine, read directly from StdIn
      StdIn.readLine() match {
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("move") && arg.equalsIgnoreCase("north") => {
          if(currentRoom.getNorth() != "null") {
            moveNorth()
          } else {
            println("There is no room to the North!")
          }
        }
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("move") && arg.equalsIgnoreCase("east") => {
          if(currentRoom.getEast() != null) {
            moveEast()
          } else {
            println("There is no room to the East!")
          }
        }
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("move") && arg.equalsIgnoreCase("south") => {
          //if the user finishes the maze
          if(currentRoom.getRoomID() == "R03") {
            println(s"Congratulations, $name, you went through $pathLength doors to get to the exit!")
            continueMenuLoop = false
            db.addOne(name, pathLength, path.toList, true)
            db.closeConnection()
          }
          else if(currentRoom.getSouth() != null) {
            moveSouth()
          } else {
            println("There is no room to the South!")
          }
        }
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("move") && arg.equalsIgnoreCase("west") => {
          if(currentRoom.getWest() != null) {
            moveWest()
          } else {
            println("There is no room to the West!")
          }
        }
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("look") => {currentRoom.printDesc()}
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("moves") => {printMoves(pathLength)}
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("path") => {
          for (i <- path) print(s"$i, ")
          println()
        }
        case commandArgPattern(cmd, arg) if cmd.equalsIgnoreCase("exit") => {
          println(s"You gave up after taking $pathLength doors.")
          db.addOne(name, pathLength, path.toList, false)
          db.closeConnection()
          continueMenuLoop = false
        }
        case notRecognized => println(s"$notRecognized not a recognized command")
      }
    }
  }
}

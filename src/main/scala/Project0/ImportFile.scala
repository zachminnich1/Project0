package Project0

import java.io.FileNotFoundException

import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}

class ImportFile {

  /** Populates list of rooms with data from file*/
  def csv(filename: String, roomList: List[Room]): Unit = {

    val listOfItems = ArrayBuffer[Array[String]]()

    var file :BufferedSource = null

    try {
      file = Source.fromFile(filename)

      //takes every row from file, splits them on the commas, then adds them to list
      for (item <- file.getLines) listOfItems.addOne(item.split(","))

      //goes through every room and adds the data from the file
      for(num <- 0 to roomList.length-1) roomList(num).setAll(listOfItems(num))
    }
    finally {
      if (file != null) file.close
    }
  }

}

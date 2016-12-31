package fileSearcher

import java.io.{FileWriter, PrintWriter}

/**
  * Created by hanifnorman on 31/12/16.
  */
object SearchResultWriter {
  def writeToFile(filePath: String, searchResults: List[(String, Option[Int])]) = {
    val fileWriter = new FileWriter(filePath)
    val printWriter = new PrintWriter(fileWriter)

    try
      for((fileName, countOption) <- searchResults)
        printWriter.println(countOption match {
          case Some(count) => s"$fileName -> $count"
          case None => s"$fileName"
        })
    finally {
      printWriter.close()
      fileWriter.close()
    }
  }
}

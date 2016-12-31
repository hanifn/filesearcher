package fileSearcher

import java.io.{FileWriter, PrintWriter}

/**
  * Created by hanifnorman on 31/12/16.
  */
object SearchResultWriter {

  def writeToConsole(results: List[(String, Option[Int])]) =
    for((fileName, countOption) <- results)
      println(getString(fileName, countOption))

  private def getString(fileName: String, countOption: Option[Int]) =
    countOption match {
      case Some(count) => s"\t$fileName -> $count"
      case None => s"\t$fileName"
    }

  def writeToFile(filePath: String, searchResults: List[(String, Option[Int])]) = {
    val fileWriter = new FileWriter(filePath)
    val printWriter = new PrintWriter(fileWriter)

    try
      for((fileName, countOption) <- searchResults)
        printWriter.println(getString(fileName, countOption))
    finally {
      printWriter.close()
      fileWriter.close()
    }
  }
}

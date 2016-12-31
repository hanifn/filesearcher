package fileSearcher

import java.io.File

import scala.util.control.NonFatal
import scala.util.matching.Regex

/**
  * Created by hanifnorman on 30/12/16.
  */
class FilterChecker(filter: String) {
  val filterAsRegex: Regex = filter.r
  def matches(content: String): Boolean =
    filterAsRegex findFirstMatchIn content match {
      case Some(_) => true
      case None => false
    }

  def findMatchedFiles(iOObjects: List[IOObject]): List[IOObject] =
    for (iOObject <- iOObjects
      if iOObject.isInstanceOf[FileObject]
      if matches(iOObject.name))
    yield iOObject

  def findMatchedContentCount(file: File): Int = {
    def getFilterMatchCount(content: String) =
      (filterAsRegex findAllIn content).length

    import scala.io.Source

    try {
      val fileSource = Source.fromFile(file)
      try
        fileSource.getLines().foldLeft(0)(
          (acc, line) => acc + getFilterMatchCount(line)
        )
      catch {
        case NonFatal(_) => 0
      }
      finally
        fileSource.close()
    } catch {
      case NonFatal(_) => 0
    }
  }
}

object FilterChecker {
  def apply(filter: String): FilterChecker = new FilterChecker(filter)
}

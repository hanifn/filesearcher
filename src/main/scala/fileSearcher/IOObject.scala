package fileSearcher

import java.io.File

import scala.util.control.NonFatal

/**
  * Created by hanifnorman on 30/12/16.
  */

trait IOObject {
  val file: File
  val name: String = file.getName
  val fullName = try file.getAbsolutePath catch { case NonFatal(_) => name }
}

case class FileObject(file: File) extends IOObject
case class DirectoryObject(file: File) extends IOObject {
  def children(): List[IOObject] =
    try
      file.listFiles().toList map(file => FileConverter convertToIOObject file)
    catch {
      case _ : NullPointerException => List()
    }

}

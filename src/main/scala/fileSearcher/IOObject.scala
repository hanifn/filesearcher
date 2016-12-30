package fileSearcher

import java.io.File

/**
  * Created by hanifnorman on 30/12/16.
  */

trait IOObject {
  val file: File
  val name = file.getName()
}

case class FileObject(file: File) extends IOObject
case class DirectoryObject(file: File) extends IOObject {
  def children() =
    try
      file.listFiles().toList map(file => FileConverter convertToIOObject file)
    catch {
      case _ : NullPointerException => List()
    }

}

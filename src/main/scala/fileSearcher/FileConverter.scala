package fileSearcher

import java.io.File

/**
  * Created by hanifnorman on 31/12/16.
  */
object FileConverter {
  def convertToIOObject(file: File) =
    if (file.isDirectory()) DirectoryObject(file)
    else FileObject(file)
}

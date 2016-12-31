package fileSearcher

import java.io.File

import org.scalatest.FlatSpec

/**
  * Created by hanifnorman on 30/12/16.
  */
class MatcherTests extends FlatSpec{
  "Matcher that is passed a file matching the filter" should
  "return a list with file name" in {
    val matcher = new Matcher("fake", "fakePath")

    val results = matcher.execute()

    assert(results == List("fakePath"))
  }

  "Matcher using a directory containing one file matching the filter" should
  "return a list with that file name" in {
    val matcher = new Matcher("txt", new File("./testfiles").getCanonicalPath)

    val results = matcher.execute()

    assert(results == List("readme.txt"))
  }

  "Matcher that is not passed a root file location" should
  "use the current location" in {
    val matcher = new Matcher("filter")
    assert(matcher.rootLocation == new File(".").getCanonicalPath)
  }

  "Matcher with sub folder checking matching a root location with two subtree files matching the filter" should
  "return a list with those files names" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", new File("./testfiles").getCanonicalPath,
      searchSubDirectories)
    val results = matcher.execute()

    assert(results == List("notes.txt", "readme.txt"))
  }

  "Matcher given a path that has one file that matches file filter and content filter" should
  "return a list with that file name" in {
    val matcher = new Matcher("data", new File(".").getCanonicalPath, true, Some("pluralsight"))

    val matchedFiles = matcher.execute()

    assert(matchedFiles == List("pluralsight.data"))
  }

  "Matcher given a path that has no file that matches file filter and content filter" should
  "return an empty list" in {
    val matcher = new Matcher("txt", new File(".").getCanonicalPath, true, Some("pluralsight"))

    val matchedFiles = matcher.execute()

    assert(matchedFiles == List())
  }
}

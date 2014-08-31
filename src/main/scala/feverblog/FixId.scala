package feverblog

import java.io.File

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup

import scala.collection.JavaConversions._

object FixId extends App {

  def getRemotePosts = {
    val doc = Jsoup.parse(new File("data/index.html"), "UTF-8")
    doc.select(".entry-content li a").map { a =>
      val href = a.attr("href")
      val title = a.text()
      val IdPattern = """.*/(\d+)\.html""".r
      val IdPattern(id) = href
      println("id: " + id)
      new RemotePost(id, title)
    }
  }

  def getLocalPosts = {
    val postsRoot = new File("/Users/freewind/blog/_posts")
    val postFiles = FileUtils.listFiles(postsRoot, Array("md"), true)
    postFiles.map { f =>
      val lines = FileUtils.readLines(f, "UTF-8")
      val titleLine = lines.find(_.startsWith("title:")).get
      val title = titleLine.substring(6).trim
      LocalPost(title, f)
    }
  }


  val remotePosts = getRemotePosts

  val localPosts = getLocalPosts

  localPosts.foreach { l =>
    val matched = remotePosts.filter(_.title == l.title)
    //    println(matched)
    if (matched.isEmpty) {
      println("Can't find matched for: " + l)
    } else if (matched.length > 1) {
      println("More than 1: " + matched)
    } else {
      val lines = FileUtils.readLines(l.file)
      lines.insert(2, "id: " + matched.head.id)
      FileUtils.writeLines(l.file, "UTF-8", lines)
    }

  }

  case class RemotePost(id: String, title: String)

  case class LocalPost(title: String, file: File)

}

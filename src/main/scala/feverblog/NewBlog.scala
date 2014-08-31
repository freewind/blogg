package feverblog

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

import org.apache.commons.io.FileUtils

object NewBlog extends App {

  val today = new SimpleDateFormat("yyyy-MM-dd").format(new Date)
  val now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date)
  val newPostName = s"$today-???.md"

  val rootCategory = PostFiles.find(postRoot)

  import Utils._

  val newPostId = GenerateNewPostId(allPosts(rootCategory))

  val template =
    s"""
      |---
      |layout: post
      |id: $newPostId
      |alias: ???
      |tags: ???
      |date: $now
      |title: ???
      |---
    """.stripMargin.trim

  val targetFile: File = new File(postRoot, newPostName)

  FileUtils.writeStringToFile(targetFile, template, "UTF-8")

  println("targetFile: " + targetFile)
}

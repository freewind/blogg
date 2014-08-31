package feverblog

import java.io.File

import org.apache.commons.io.FileUtils
import scala.collection.JavaConversions._

object FixImages extends App {
  val postRoot = new File("/Users/freewind/blog/_posts")

  val postFiles = FileUtils.listFiles(postRoot, Array("md"), true)

  postFiles.foreach { p =>
    val content = FileUtils.readFileToString(p, "UTF-8")

    val id = FileUtils.readLines(p, "UTF-8").find(_.startsWith("id:")).get.substring(3).trim


    val ImageLink = """http://freewind.me/wp-content/uploads/([-\w/.]+)""".r

    var imageLinkMap = Map.empty[String, String]


    var index = 0
    ImageLink.findAllMatchIn(content).foreach { x =>
      val imageLink = x.group(0)
      val localImagePath: String = "/Users/freewind/blogsite/images/" + x.group(1)
      val newImageFileName = id + "-" + index + "." + imageLink.split("[.]").last
      index += 1
      val newImagePath = new File("/Users/freewind/blogsite/images/" + newImageFileName)
      println("### imageLink: " + imageLink)
      println("### newImagePath: " + newImagePath)
      imageLinkMap += (imageLink -> ("/user_images/" + newImageFileName))
      FileUtils.copyFile(new File(localImagePath), newImagePath)
    }

    println("########### imageLinkMap: " + imageLinkMap)

    val fixed = imageLinkMap.foldLeft(content) {
      case (t, (k, v)) => t.replace(k, v)
    }

    FileUtils.writeStringToFile(p, fixed, "UTF-8")


  }

}

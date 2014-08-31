package feverblog

import java.io.File
import java.net.URL

import org.apache.commons.io.FileUtils

import scala.sys.process._

object GetImages extends App {

  val root = new File("/Users/freewind/blog/_posts")

  def fixDir(dir: File): Unit = {
    dir.listFiles.toList.filter(f => f.isFile || f.isDirectory).partition(_.isFile) match {
      case (files, dirs) =>
        files.foreach(fixArticle)
        dirs.foreach(fixDir)
    }
  }

  def fixArticle(file: File): Unit = {
    val content = FileUtils.readFileToString(file, "UTF-8")

    val ImageLink = """http://freewind.me/wp-content/uploads/([-\w/.]+)""".r

    ImageLink.findAllMatchIn(content).foreach { x =>
      val imageLink = x.group(0)
      val targetPath: String = "/Users/freewind/blogsite/images/" + x.group(1)

      if (!new File(targetPath).exists()) {
        new File(targetPath).getParentFile.mkdirs()
        println("### imageLink: " + imageLink)
        (new URL(imageLink) #> new File(targetPath)).!!
        println("# " + targetPath)
        Thread.sleep(500)
      }

    }
  }

  fixDir(root)

}

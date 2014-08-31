import java.io.File

import scala.collection.JavaConversions._

package object feverblog {

  val postRoot = new File("/Users/freewind/blog/_posts")

  val siteRoot = new File("/Users/freewind/workspace/freewind.github.io")

  val siteConfig = new SiteConfig("Freewind @ Thoughtworks", "http://freewind.github.io", "UA-54316895-1")

}

package feverblog.pages

import java.util.{List => JList}

import feverblog._
import feverblog.models.Post


object PostPage {

  def generate(post: Post): String = {
    case class Data(siteConfig: SiteConfig, article: Post)
    val template = new Template[Data]("post")
    template.render(Data(siteConfig, post))
  }

}

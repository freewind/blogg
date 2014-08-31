package feverblog.pages

import java.util.{Date, List => JList}

import feverblog.Utils._
import feverblog._
import feverblog.models.RootCategory

import scala.collection.JavaConversions._

object FeedPage {

  case class FeedItem(title: String, link: String, pubDate: String, creator: String, category: String, content: String, guid: String)

  case class Data(siteConfig: SiteConfig, items: JList[FeedItem], lastBuildDate: String)

  def generate(rootCategory: RootCategory): String = {
    val items = allPosts(rootCategory).sortWith(timeDesc).map(a =>
      FeedItem(a.title, a.link, a.dateAsPubDate, "Freewind", a.category.map(_.name).getOrElse("未分类"), a.contentAsHtml, a.id)
    )
    val template = new Template[Data]("feed")
    template.render(Data(siteConfig, items, lastBuildDate))
  }

  def lastBuildDate = formatAsRssDate(new Date)
}

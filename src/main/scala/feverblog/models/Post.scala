package feverblog.models

import java.text.SimpleDateFormat
import java.util.{Date, List => JList}

import feverblog.Utils._
import feverblog._
import org.markdown4j.Markdown4jProcessor

case class Post(id: String, title: String, content: String, alias: String, date: Date, layout: String, tags: List[String], category: Option[Category] = None) {
  def contentAsHtml: String = {
    new Markdown4jProcessor().process(content)
  }

  def dateAsPubDate: String = formatAsRssDate(date)

  def dateOnly: String = new SimpleDateFormat("yyyy-MM-dd").format(date)

  def link: String = Links.postLink(this)
}

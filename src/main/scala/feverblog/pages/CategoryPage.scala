package feverblog.pages

import java.util.{List => JList}

import feverblog.Utils._
import feverblog._
import feverblog.models.{Category, Post}

import scala.collection.JavaConversions._

object CategoryPage {

   case class Data(siteConfig: SiteConfig, category: Category, articles: JList[Post])

   def generate(category: Category): String = {
     val template = new Template[Data]("category")
     val articles = articlesOfCategory(category).sortWith(timeAsc)
     template.render(Data(siteConfig, category, articles))
   }
 }

package feverblog.pages

import java.util.{List => JList}

import feverblog.Utils._
import feverblog._
import feverblog.models.{Category, Post, RootCategory}

import scala.collection.JavaConversions._

object IndexPage {

   def generate(rootCategory: RootCategory): String = {
     case class Data(siteConfig: SiteConfig, categories: JList[Category], articles: JList[Post])
     val articles = allPosts(rootCategory).sortWith(timeDesc)
     val categories = allFirstLevelCategories(rootCategory)
     val template = new Template[Data]("index")
     template.render(Data(siteConfig, categories, articles))
   }
 }

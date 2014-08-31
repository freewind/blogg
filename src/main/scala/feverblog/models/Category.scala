package feverblog.models

case class Category(order: Int, alias: String, name: String, articles: List[Post], subCategories: List[Category])

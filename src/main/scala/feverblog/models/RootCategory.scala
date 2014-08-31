package feverblog.models

case class RootCategory(articles: List[Post], subCategories: List[Category])

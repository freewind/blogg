package feverblog

import feverblog.models.Post

object GenerateNewPostId {

  def apply(posts: List[Post]): Int = {
    val x = posts.map(_.id.toInt)
    if (x.toSet.size != x.size) {
      throw new RuntimeException("there are duplicated ids")
    }
    x.max + 1
  }

}

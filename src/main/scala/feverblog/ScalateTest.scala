package feverblog


import java.io.File

import org.fusesource.scalate.{Binding, RenderContext, TemplateEngine}

object ScalateTest extends App {

  val engine = TemplateEngine(Seq(new File("/Users/freewind/workspace/blogg/templates")), "d")
  val temp = engine.compileSsp("index", Seq(Binding("user")))
  val s = temp.render(RenderContext.apply())

}



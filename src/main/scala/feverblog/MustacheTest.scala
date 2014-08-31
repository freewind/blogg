package feverblog

import java.io.PrintWriter

import com.github.mustachejava.DefaultMustacheFactory

object MustacheTest extends App {

  println("###")

  val mf = new DefaultMustacheFactory()
  val mustache = mf.compile("templates/me.mustache");
  mustache.execute(new PrintWriter(System.out), new User("Freewind")).flush();

}

case class User(name: String)

package feverblog.pages

import java.io.{ByteArrayOutputStream, PrintWriter}

import com.github.mustachejava.DefaultMustacheFactory

class Template[T](name: String) {
   def render(data: T): String = {
     val mf = new DefaultMustacheFactory()
     val mustache = mf.compile(s"templates/$name.mustache");

     val out = new ByteArrayOutputStream
     mustache.execute(new PrintWriter(out), data).flush();
     out.toString("UTF-8")
   }
 }

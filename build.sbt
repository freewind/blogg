name := "blogg"

version := "0.0.1"

resolvers ++= Seq(
  "ibiblio" at "http://mirrors.ibiblio.org/pub/mirrors/maven2"
)

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "org.commonjava.googlecode.markdown4j" % "markdown4j" % "2.2-cj-1.0",
  "org.scalatra.scalate" %% "scalate-web" % "1.7.0",
  "com.github.spullara.mustache.java" %	"compiler" %	"0.8.16",
  "org.jsoup" % "jsoup" % "1.7.3"
)



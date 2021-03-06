val ScalatraVersion = "2.6.2"

organization := "com.shortu"

name := "short url web app"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.4"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",

  "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test",
  "net.debasishg" %% "redisclient" % "3.4"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)

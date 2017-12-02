package com.shortu.app

import org.scalatra._
import scalate.ScalateSupport

class ShortUrlServlet extends MyScalaLinkShortenerStack {

  get("/") {
  	contentType="text/html"
    layoutTemplate("index")
  }

  get("/:link"){
  	val link = params("link")
    println(link)
  	contentType="text/html"
  	val x:String = LinkController.getLink("sdfsd")
    LinkController.getLink(link.toString) match {
      case str:String => redirect(str)
      case null => serveStaticResource() getOrElse resourceNotFound()
    }
    layoutTemplate("index")
  }

  post("/"){
    val link = params("link")
    val outputTemplate = layoutTemplate("link", _:(String,Any), _:(String,String))
    contentType = "text/html"

    /* 
       using hash of full url or web link
    */
    /*LinkController.setLink(link) match {
      case hashed: String => outputTemplate("link" -> hashed, "oldLink" -> link)
      case _ => outputTemplate("displayForm" -> true, "oldLink" -> link)
    }*/

    /*
       using base 62 random token or key
    */
    LinkController.getNextToken(link) match {
    	case token: String => {
                  println(token)
                  outputTemplate("link" -> token, "oldLink" -> link)
                }
    	case _=> outputTemplate("displayForm" -> true, "oldLink" -> link)	
    }
  }
}
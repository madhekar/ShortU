package com.shortu.app

import org.scalatra._

class ShortUrlServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }

}

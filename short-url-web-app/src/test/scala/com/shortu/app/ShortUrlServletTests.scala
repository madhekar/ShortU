package com.shortu.app

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike

class ShortUrlServletTests extends ScalatraSuite with FunSuiteLike {

  addServlet(classOf[ShortUrlServlet], "/*")

  test("GET / on ShortUrlServlet should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}

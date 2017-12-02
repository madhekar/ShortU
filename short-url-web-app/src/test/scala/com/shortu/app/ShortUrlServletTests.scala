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

  test("POST to / ShortUrlServlet should return status 200"){
  	post("/http://www.apple.com/mac"){
  		status should equal (200)
  	}
  }

  test("GET / on token should return 200 or 302, for www.apple.com if not after post"){
  	get("/LBeYkb9X"){
  		status should (equal(302) or equal(200))
  	}
  }
}

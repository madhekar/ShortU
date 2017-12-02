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

  test("POST to valid url ShortUrlServlet should return status 200"){
  	post("/http://www.apple.com/mac"){
  		status should equal (200)
  	}
  }
  /*
     RFC 1945 and RFC 2068 specify that the client is not allowed to change the method on the redirected 
     request. However, most existing user agent implementations treat 302 as if it were a 303 response, 
     performing a GET on the Location field-value regardless of the original request method. 
     The status codes 303 and 307 have been added for servers that wish to make unambiguously clear which 
     kind of reaction is expected of the client.
  */

  test("POST to invalid url on ShortUrlServlet should return status"){
  	post("/htt://www.amazon.com"){
  		status should (equal(302) or equal(200))
  	}
  }
}

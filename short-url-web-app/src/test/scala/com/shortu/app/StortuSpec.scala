package com.shortu.app

import org.scalatest.FlatSpec
import org.scalatest._
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import Matchers._

class ShortUSpec extends FlatSpec with Matchers with WebBrowser{
	implicit val webDriver : WebDriver = new HtmlUnitDriver
	val host = "http://localhost:8080/"

    "The Url Shortener home page" should "have the correct title" in {
	   go to (host + "index.html")
	   pageTitle should be ("Welcome to the Scala Url Shortener")
    }

    "submit Url to shorten" should "return valid link" in {
       go to (host + "index.html")	
	   click on "link"
	   enter("http://www.apple.com/mac/")
	   submit()
	   val x = webDriver.findElement(By.id("link")) 
	   print("x: ", x.getText)
	   x should not be ("")
    }

    "submit Url to shorten" should "return valid link or token" in {
       go to (host + "index.html")	
	   click on "link"
	   enter("http://www.apple.com/mac/")
	   submit()
    }


}
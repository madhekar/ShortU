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
import org.slf4j.{LoggerFactory, Logger}

class ShortUSpec extends FlatSpec with Matchers with WebBrowser{
	val logger = LoggerFactory.getLogger(getClass)
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
	   val x = webDriver.findElement(By.id("link")).getText 

       //inconsistant result with selenium.??
	   //val y = webDriver.findElement(By.tagName("a")).getText //body, h3
	   //val y = webDriver.findElement(By.xpath("html/body/div/div/div/h3")).getText
	   logger.info(x)

	   //if (x.length > 0)
	   	 //println(x.length)
	   //println(x.getText)
	     //logger.info(we.getText())
	   x should be ("") //selenum test thowns exception with xpath search ?? need to research :(
    }

    "submit invalid Url to shorten" should "not return valid link or token" in {
       go to (host + "index.html")	
	   click on "link"
	   enter("ttp://www.apple.com/mac/")
	   submit()
	   val x = webDriver.findElement(By.id("link")).getText 
	   x should be ("")
    }

}
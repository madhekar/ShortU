package com.shortu.app
import scala.util.hashing.MurmurHash3
import com.redis._

object RedisWrapper {
  val redis = new RedisClient("localhost", 6379)
}

//Object based version, like this one because it's more simple than the other
object validateUrl {
  def apply(inputUrl:String): Any = {
    val validUrl = "^(https?|ftp|file)://.[a-zA-Z]{1,3}[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]*?".r
        inputUrl match {
          case validUrl(_*) => true
          case _ => false
        }
  }
}
/*
  tried two ways to implement creation of short url. That calls for comprihensive understanding of product
  how many users, access frequency that technically dictates the size of key. This is becuase one also
  needs to consider valid characters supported by URL standards. That leads to 62 characters could be used 
  to create a key i.e. a-z,A-Z,0-9 technically additional symbol chars could also be used incase roughly 
  7 char not acceptable. base conversion to 62 gives quite a bit flexibility and range. Additionally 
  TTL could be engineered for each key based on either last use i.e. if link is not used for 90 days 
  could be evicted from redis.
*/

object LinkController{
  val validChars = ('a' to 'z') ++ ('A' to 'Z') ++ (0 to 9)

  def randomChar = validChars(scala.util.Random.nextInt(validChars.size))

  /*
     generate random key based on 62 charactors and persist in redis with input url
     valid charctors a-z A-Z and 0-9 => combination of unique 7 charactors token/ key
  */
  def getNextToken(inputLink:String): Any = {
      var nextValidToken = (1 to 8).foldLeft("")((acc,_) => acc + randomChar).toString
      if (validateUrl(inputLink) == true){
        val rv = RedisWrapper.redis.get(inputLink).orNull
          if (rv != null){
            nextValidToken = rv
          }else{
        val x = RedisWrapper.redis.set(nextValidToken, inputLink)
        val y = RedisWrapper.redis.set(inputLink, nextValidToken)
      }
        /*println(x, nextValidToken)*/
        nextValidToken
      } else {
        false
      }
  }

  def getLink(shortenedLink:String): String = {
    /*println(shortenedLink)*/
    RedisWrapper.redis.get(shortenedLink).orNull
  }

  def setLink(inputLink:String): Any = {
    val hashedLink = MurmurHash3.stringHash(inputLink).toString
    if (validateUrl(inputLink) == true){
      val x = RedisWrapper.redis.set(hashedLink, inputLink)
      /*println(x)*/
      hashedLink
    }
    else {
       false
    }
  }
}


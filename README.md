# ShortU
Url Shortner POC

This is proof of concept application for many reasons stated in following sections, To begin with
This application implements URL shortening functionality user is expected to provide a valid url, 
in response application generates a shortened link in 7 characters. Architecture realize on base 62
randomness to generate unique links. 

***Limitations:***
- This could be resolved by checking if shortened key already existing redis data store. which is overkill
for prototype.
- some keys will be duplicate to handle requirement which mandates, not generating keys for already shortened urls. This is performance decision over bit extra storage cost.
- 


Assumptions:

  1. user is expected to supply valid URL https://tools.ietf.org/html/rfc3986#page-11
  2. There is some assumption on how many users would access this service and approximate 
     rate at which they would request this app.
  3. one of the critical requirement was to detect if url is already shortened, this app makes
     assumption that re-shortening same url will be minimum. this is due to limitation of scala
     library to have access some of the redis api.

Architecture:

  1. this POC chooses to use jetty servlet/ web container 
  2. Scalate template engine for Jade template format for HTML markup generation is adopted for simplicity
  3. ingestion Scala code to manage the template mapping under templates folder  
  4. application testing although not comprehensive or extensive for this application. The effort has made to incorporate sample test cases.
  5. Test framework used is based on Scalatest, in addition few selenium web app test are also added
  6. refer to Architecture.pdf file to production deployment example based on HAproxy based load balancing of
  redis master-worker(slave) configuration for redundancy.
  
Application notes:

generate scalatra based web app from scratch:
sbt new scalatra/scalatra.g8


Setting up Redis (Mac):

follow instructions on https://redis.io/download
1. wget http://download.redis.io/releases/redis-4.0.5.tar.gz
2. tar xzf redis-4.0.5.tar.gz 
3. cd redis-4.0.5
4. run make to build "C" code base

To start redis server:
$ src/redis-server

To strat redis client:
$ src/redis-cli

Simple commands to validate K-V records in redis
get all keys
$ keys *

get specific value for key eg.key = "LBeYkb9X"
$ get LBeYkb9X  

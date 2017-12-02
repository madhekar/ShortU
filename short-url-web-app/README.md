# short url web app #

## Build & Run ##

```sh
$ cd short_url_web_app
$ ./sbt
> jetty:start
> test or
> browse

> exit 
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

# automatic code compile and run
sbt
> ~;jetty:stop;jetty:start

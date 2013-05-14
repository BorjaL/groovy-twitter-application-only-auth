groovy-twitter-application-only-auth
====================================

A groovy implementation of the Twitter Application-only authentication
https://dev.twitter.com/docs/auth/application-only-auth

Usage
-----

```groovy
  def consumer_key = "exampleOfConsumerKey"
  def consumer_secret = "exampleOfSecretKey"
  new TwitterConsumer(query: "BorjaL", consumer_key: consumer_key, consumer_secret: consumer_secret).searchAuthentificationApplicationOnly()
```

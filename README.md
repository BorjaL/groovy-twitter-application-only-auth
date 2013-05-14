groovy-twitter-application-only-auth
====================================

A groovy implementation of the Twitter Application-only authentication
https://dev.twitter.com/docs/auth/application-only-auth

Example
-----

```groovy
  new TwitterConsumer(
                query: "BorjaL", 
                consumer_key: "exampleOfConsumerKey", 
                consumer_secret: "exampleOfSecretKey"
                    ).searchAuthentificationApplicationOnly()
```

import org.apache.commons.codec.binary.Base64;
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.POST
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.JSON

class TwitterConsumer {

    def query
    def consumer_key
    def consumer_secret

    def searchAuthentificationApplicationOnly() {
		def locale = localeForQuery()
		def access_token = getAccessToken()
		def tweets = []

		def http = new HTTPBuilder( "https://api.twitter.com/1.1/search/tweets.json?q=${query}&lang=${locale}&count=10" )
		http.request(GET,JSON) { req ->

			headers.'Authorization' = 'Bearer ' + access_token
			headers.'Accept-Encoding' = 'gzip'

			response.success = { resp, json ->
        		json.statuses.each {
        			tweets << [
                                    text: it.text, 
                                    createdAt: it.created_at.minus("+0000"), 
                                    user: it.user.screen_name
                              ]
        		}
        	}

        	response.failure = { resp ->
        		log.error "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
        	}
		}

		return tweets
	}

	private getAccessToken(){
        def encoded_bearer_token = new String(Base64.encodeBase64((consumer_key+":"+consumer_secret).bytes))

        def http = new HTTPBuilder( 'https://api.twitter.com' )

        def access_token

        http.request(POST,JSON) { req ->
        	uri.path = "/oauth2/token"
        	headers.'Content-Type'  = 'application/x-www-form-urlencoded;charset=UTF-8'
        	headers.'Authorization' = 'Basic ' + encoded_bearer_token
        	headers.'Accept-Encoding' = 'gzip'
        	
        	body = 'grant_type=client_credentials'

        	response.success = { resp, json ->
        		access_token = json.access_token
        	}

        	response.failure = { resp ->
        		log.error "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
        	}
        }

        return access_token
    }
}
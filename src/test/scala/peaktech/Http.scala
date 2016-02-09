package peaktech

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Http {
	var url = System.getProperty("url")

	if (url == null) {
		url = "http://staging.xn--90aiim0b4c.xn--e1afgsrt.xn--p1ai"
	}

	val protocol = http
		.baseURL(url)
		.acceptLanguageHeader("en-US,en;q=0.5")
    	.acceptEncodingHeader("gzip, deflate")
    	.acceptHeader("application/json, text/plain, */*")
    	.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
}

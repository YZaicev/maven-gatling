package peaktech

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Site {
	val export = feed(jsonFile("data.json").random)
		.exec(http("export")
		.get("/site/${tenant}/export")
		.header("Authorization", "Bearer ${token}")
		.check(status.is(200)))
}

package peaktech

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object User {
	val login = exec(http("/user/login")
		.post("/user/login")
		.body(RawFileBody("login.json")).asJSON
		.check(status.is(200))
		.check(jsonPath("$..token").saveAs("token"))
		.check(jsonPath("$..user").ofType[Any].saveAs("user")))

	val checkProfile = exec(http("profile")
		.get("/user/${user.id}")
		.header("Authorization", "Bearer ${token}")
		.check(status.is(200)))
}

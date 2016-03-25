package peaktech

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object User {
	def login =
		exec(http("login")
			.post("/account/login_user")
			.body(RawFileBody("login.json")).asJSON
      .header("Authorization", session => Http.getAuthorizationHeader(""))
			.check(status.is(200))
      .check(header("Authorization").saveAs("auth")))

  val getAccount =
    exec(http("get account")
    .get("/account")
    .header("Authorization", session => Http.getAuthorizationHeader(session("auth").as[String]))
    .check(status.is(200)))
}

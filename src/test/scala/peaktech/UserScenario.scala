package peaktech

import scala.concurrent.duration._
import io.gatling.core.Predef._

import User._
import Http._

class UserScenario extends Simulation {
	val scn = scenario("User Tests").exec(User.login, User.checkProfile)
	setUp(scn.inject(rampUsers(20) over (10 seconds))).protocols(Http.protocol)
}

package peaktech

import io.gatling.core.Predef._

class UserScenario extends Simulation {
  val scn = scenario("User Scenario").exec(User.login, User.getAccount)
  setUp(scn.inject(atOnceUsers(10)).protocols(Http.protocol))
}

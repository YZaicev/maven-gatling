package peaktech

import scala.concurrent.duration._
import io.gatling.core.Predef._

import Site._
import Http._

class DeviceScenario extends Simulation {
	val scn = scenario("Export").exec(Site.export)
	setUp(scn.inject(rampUsers(10) over (10 seconds))).protocols(Http.protocol)
}

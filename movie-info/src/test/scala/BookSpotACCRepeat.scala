import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


class BookSpotACC extends Simulation {

    val httpProtocol: HttpProtocolBuilder = http
            .baseUrl("https://acc.bookspot.nl")

    object Filter {
        val advFilter: ChainBuilder = exec(
                http("SearchProduct")
                        .post("/INTERSHOP/rest/WFS/tba-bookspot_nl-Site/-/advfilter")
                        .header("Content-Type", "application/json")
                        .body(StringBody("""{"categoryID": "","pageSize": 24,"pageRequest": ${n},"selectedFilters": [],"term": "*","searchParameter": null}"""))
                        .check(status.is(200)))
    }

    val scenarioGetMovies =  scenario("SearchProduct").repeat(10,"n")
            exec(Filter.advFilter)

   setUp(scenarioGetMovies.inject(
            incrementUsersPerSec(0)
      .times(1)
      .eachLevelLasting(1)
      .startingFrom(500))
      .protocols(httpProtocol))
}
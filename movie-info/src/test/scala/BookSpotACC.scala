package com.springboot.ms.movieinfo.performance.test

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


class BookSpotACC extends Simulation {

    val httpProtocol: HttpProtocolBuilder = http
            .baseUrl("https://acc.bookspot.nl")

    object MovieResource {
        val getMovies: ChainBuilder = exec(
                http("SearchProduct")
                        .post("/INTERSHOP/rest/WFS/tba-bookspot_nl-Site/-/advfilter")
                        .header("Content-Type", "application/json")
                        .body(StringBody("""{"categoryID": "","pageSize": 24,"pageRequest": 2,"selectedFilters": [],"term": "*","searchParameter": null}"""))
                        .check(status.is(200)))
    }

    val scenarioGetMovies =  scenario("SearchProduct").
            exec(MovieResource.getMovies)

    setUp(scenarioGetMovies.inject(
            incrementUsersPerSec(20)
      .times(5)
      .eachLevelLasting(5)
      .separatedByRampsLasting(5)
      .startingFrom(10))
      .protocols(httpProtocol))
}

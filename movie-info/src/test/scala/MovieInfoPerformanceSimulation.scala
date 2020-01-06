package com.springboot.ms.movieinfo.performance.test

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


class MovieInfoPerformanceSimulation extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8180")

  object MovieResource {
    val getMovies: ChainBuilder = exec(
                              http("GetMovies")
                              .get("/movies")
                              .check(status.is(200)))
    }

  val scenarioGetMovies =  scenario("GetMovies").
                            exec(MovieResource.getMovies)



  setUp(scenarioGetMovies.inject(
    incrementUsersPerSec(20)
      .times(5)
      .eachLevelLasting(5)
      .separatedByRampsLasting(5)
      .startingFrom(10))
    .protocols(httpProtocol))
}

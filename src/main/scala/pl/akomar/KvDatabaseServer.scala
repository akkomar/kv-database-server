package pl.akomar

import com.google.inject.{Inject, Module}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.request.QueryParam

object KvDatabaseServerMain extends KvDatabaseServer

class KvDatabaseServer extends HttpServer {

  //instead of setting -http.port flag when starting process:
  override val defaultFinatraHttpPort: String = ":4000"

  override val modules: Seq[Module] = Seq(DataStoreModule)

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[DataController]
  }
}

class DataController @Inject()(data: DataStore) extends Controller {

  get("/get") { request: GetRequest =>
    data.get(request.key)
  }

  put("/set") { request: Request =>
    val keyToSetOption = request.params.headOption

    keyToSetOption match {
      case Some(keyToSet) =>
        data.set(keyToSet._1, keyToSet._2)
        "ok"
      case None =>
        "Error: no key to set provided"
    }
  }
}

case class GetRequest(@QueryParam key: String)

package pl.akomar

import org.scalatest.{FunSuite, Matchers}

class DataStoreTest extends FunSuite with Matchers {

  val dataStore = new DataStore()

  test("Datastore should persist data") {
    dataStore.set("key", "value")
    dataStore.get("key") shouldBe Some("value")

    dataStore.get("unsetKey") shouldBe None
  }
}

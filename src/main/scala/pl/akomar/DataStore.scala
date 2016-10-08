package pl.akomar

import java.util.concurrent.ConcurrentHashMap

import com.google.inject.{Provides, Singleton}
import com.twitter.inject.TwitterModule

object DataStoreModule extends TwitterModule {
  @Singleton
  @Provides
  def providesDataStore: DataStore = {
    new DataStore()
  }
}

class DataStore {

  val map = new ConcurrentHashMap[String, String]()

  def get(key: String): Option[String] = Option(map.get(key))

  def set(key: String, value: String): Unit = map.put(key, value)
}

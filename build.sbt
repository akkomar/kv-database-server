name := "kv-database-server"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "2.4.0",
  "com.twitter" %% "finatra-http" % "2.4.0" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)
name := "coin"
organization := "net.am.coin"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.5"
//libraryDependencies += "org.mariadb.jdbc" %% "mariadb-java-client" % "2.2.5"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.2"
//libraryDependencies ++= Seq(evolutions, jdbc)
//libraryDependencies ++= Seq(
//  "com.typesafe.play" %% "play-slick" % "3.0.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
//)
libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test


//Seq(
//  "org.reactivemongo" %% "play2-reactivemongo" % playVer,
//  "org.reactivemongo" % "reactivemongo-shaded-native" % nativeVer
//)

//libraryDependencies += "com.decodified" %% "scala-ssh" % "0.9.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "net.am.coin.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "net.am.coin.binders._"


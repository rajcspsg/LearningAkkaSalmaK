version := "1.0"
name := "chapter5"
scalaVersion := "2.12.4"
description := "Working with Akka Cluster"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-cluster
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-cluster" % "2.5.9",
  "com.typesafe.akka" %% "akka-remote" % "2.5.9",
  "com.typesafe.akka" %% "akka-persistence" % "2.5.9",
  "com.typesafe.akka" %% "akka-contrib" % "2.5.9"
)

libraryDependencies += "org.iq80.leveldb" % "leveldb" % "0.7"
libraryDependencies += "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"

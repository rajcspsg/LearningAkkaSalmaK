package routers

import Worker._
import akka.actor._

object RouterDemo extends App {

  val system = ActorSystem("routers")
  val router = system.actorOf(Props[Router], name = "router")

  router ! Work
  router ! Work
  router ! Work

  Thread.sleep(5000L)
  system.terminate()

}

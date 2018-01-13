package monitoring

import akka.actor._

object Demo extends App {
  val system = ActorSystem("system")
  val athena = system.actorOf(Props[Athena], name = "athena")
  val ares = system.actorOf(Props(new Ares(athena)), "ares")
  ares ! "ares"
  Thread.sleep(2000L)
  system.terminate()
}

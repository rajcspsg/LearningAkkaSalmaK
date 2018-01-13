package supervisiondemo

import akka.actor._

object Demo extends App {
  val system = ActorSystem("supervisor")
  val hera = system.actorOf(Props[Hera], "hera")
  hera ! "Resume"
  Thread.sleep(400)
  println()
  system.terminate()
}

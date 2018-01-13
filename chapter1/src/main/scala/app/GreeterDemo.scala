package app

import messages._
import actor._
import akka.actor._

object GreeterDemo extends App {
  val system = ActorSystem("Greet")
  val greeterActorRef = system.actorOf(Props[GreeterActor], name = "GreeterActor")
  greeterActorRef ! WhoToGreet("Main")
  println("sent message")
  Thread.sleep(3000L)
  system.terminate()
}

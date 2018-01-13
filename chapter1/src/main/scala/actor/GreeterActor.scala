package actor

import akka.actor.Actor
import messages._

class GreeterActor extends Actor {

  override def receive: Receive = {
    case WhoToGreet(who) => println(s"Hello $who")
  }
}

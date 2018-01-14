package persistent.actor

import akka.actor._
import Counter._

object PersistentDemo extends App {
  val system = ActorSystem("persistent")
  val persistentActor = system.actorOf(Props[Counter], "counter")

  persistentActor ! Increment(3)
  persistentActor ! Increment(5)
  persistentActor ! Decrement(2)

  persistentActor ! "print"

  Thread.sleep(5000L)

  system.terminate()
}

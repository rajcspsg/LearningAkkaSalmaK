package persistent.actor

import akka.actor._
import Counter._

object PersistentDemo extends App {
  val system = ActorSystem("persistent")
  val persistentActor = system.actorOf(Props[Counter], "counter")

  persistentActor ! Cmd(Increment(3))
  persistentActor ! Cmd(Increment(5))
  persistentActor ! Cmd(Decrement(2))

  persistentActor ! "print"

  Thread.sleep(50000L)

  persistentActor ! "print"
  system.terminate()
}

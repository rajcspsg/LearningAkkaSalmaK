package monitoring

import akka.actor._

class Athena extends Actor {

  override def receive: Receive = {
    case msg =>
      println("received msg $msg by Athena")
      context.stop(self)
  }
}

package routers

import akka.actor._

class Worker extends Actor {

  import Worker._

    override def receive: Receive = {
      case Work =>
        println(s"${self} received work message ")
    }

}

object Worker {
  case object Work
}

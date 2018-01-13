package monitoring
import akka.actor._

class Ares(athena: ActorRef) extends Actor {

  override def preStart(): Unit = {
    context.watch(athena)
    println(s"preStart")
  }

  override def postStop(): Unit = {
      println(s"In poststop")
  }

  override def receive: Receive = {
    case Terminated =>
      context.stop(self)
  }
}

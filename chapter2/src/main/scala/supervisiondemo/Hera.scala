package supervisiondemo

import akka.actor._
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._

class Hera extends Actor {

var childRef: ActorRef = _

import Aphrodite._
override val supervisorStrategy = OneForOneStrategy(
  maxNrOfRetries = 10,
  withinTimeRange = 1 second
) {
  case ResumeException => Resume
  case RestartException => Restart
  case StopException => Stop
  case _ => Escalate
}

  override def preRestart(reason: Throwable, msg: Option[Any]): Unit = {
    childRef = context.actorOf(Props[Aphrodite], "Aphrodite")
    Thread.sleep(500L)
  }
  override def receive: Receive = {
    case msg =>
      println(s"Hera received msg $msg")
      childRef ! msg
      Thread.sleep(500L)
  }

}

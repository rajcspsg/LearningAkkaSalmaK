package supervisiondemo

import akka.actor._

class Aphrodite extends Actor {

import Aphrodite._

  override def preStart(): Unit = {
    println(s"${self.path} preStart")
  }

  override def preRestart(reason: Throwable, msg: Option[Any]): Unit = {
    println(s"${self.path} preRestart ${reason.toString} $msg")
    super.preRestart(reason, msg);
  }

  override def postRestart(reason: Throwable): Unit = {
    println(s"${self.path} postRestart ${reason.toString}")
    super.postRestart(reason);
  }

  override def receive: Receive = {
    case "Stop" => throw  StopException
    case "Resume" => throw  ResumeException
    case "Restart" => throw  RestartException
    case _ => throw new Exception
  }

  override def postStop(): Unit = {
    println(s"${self.path} postStop")
  }
}

object Aphrodite {
  case object StopException extends Exception
  case object ResumeException extends Exception
  case object RestartException extends Exception
}

package talking.to.actors

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {

  import Recorder._
  import scala.concurrent.ExecutionContext.Implicits.global
  import Checker._
  import Storage._


  override def receive: Receive = {
    case NewUser(user) =>
      checker ? CheckUser(user) map {
        case BlackUser(user) =>
          println(s"record is the blacklist")
        case WhiteUser(user) =>
        storage ! AddUser(user)
      }
  }

}

object Recorder {
  implicit val timeout: Timeout = Timeout(5 seconds)

  sealed trait RecorderMsg
  case class NewUser(user: User) extends RecorderMsg
}

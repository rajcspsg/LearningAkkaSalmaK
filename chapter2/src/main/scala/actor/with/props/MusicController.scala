package actor.with_.props

import akka.actor.Actor

class MusicController extends Actor {
  import MusicController._
  override def receive: Receive = {
    case Play => println(s"Music Started!!!")
    case Stop => println(s"Music Stopped!!!")
  }

}

object MusicController {
  sealed trait ControllerMsg
  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg
}

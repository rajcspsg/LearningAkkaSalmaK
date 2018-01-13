package actor.with_.props

import akka.actor._

class MusicPlayer extends Actor {
  import MusicPlayer._
  override def receive: Receive = {
    case StopMusic => println(s"music can't be stopped")
    case StartMusic =>
    println(s"music is going to be started")
    val controller = context.actorOf(MusicPlayer.props, name = "controller")
    controller ! MusicController.Play
  }

}

object MusicPlayer {
  sealed trait PlayMsg
  case object StopMusic extends PlayMsg
  case object StartMusic extends PlayMsg

  def props(): Props = Props[MusicController]
}

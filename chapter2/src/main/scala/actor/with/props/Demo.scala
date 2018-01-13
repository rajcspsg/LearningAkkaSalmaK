package actor.with_.props

import MusicPlayer._
import akka.actor._

object Demo extends App {
  val system = ActorSystem("music")
  val player = system.actorOf(Props[MusicPlayer], "player")
  player ! StartMusic
  player ! StopMusic
  Thread.sleep(3000L)
  system.terminate()
}

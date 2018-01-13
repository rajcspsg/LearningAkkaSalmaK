package ref.sel.path

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object ActorIdentityDemo extends App {

  val system = ActorSystem("watch")

  implicit val timeout = Timeout(5 seconds)
  val counter = system.actorOf(Props[CounterActor], "counter")

  val counterRef = (counter ? Identify(None))
  println(s"counterRef $counterRef")
  Thread.sleep(2000L)

  val watcher = system.actorOf(Props[WatcherActor], "watcher")

  watcher ! "Identify"
  system.terminate()
}

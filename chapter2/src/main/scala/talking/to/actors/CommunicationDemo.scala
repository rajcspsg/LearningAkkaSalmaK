package talking.to.actors

import akka.actor._
import Recorder._

object CommunicationDemo extends App {
  val system = ActorSystem("communication")
  val checker = system.actorOf(Props[Checker], "checker")
  val storage = system.actorOf(Props[Storage], "storage")
  val recorder = system.actorOf(Props(new Recorder(checker, storage)), "recorder")
  recorder ! NewUser(User("jon", "jon@packt.com"))
  recorder ! NewUser(User("adam", "adam@mail.com"))
  Thread.sleep(2000L)
  system.terminate()
}

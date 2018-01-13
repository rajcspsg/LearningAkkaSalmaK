package ref.sel.path

import akka.actor._

object RefVsSel extends App {

  val system = ActorSystem("Actor-Paths")

  val counter = system.actorOf(Props[CounterActor], "counter")
  println(s"actor reference of counter1 ${counter}")

  val selection1 = system.actorSelection("counter")
  println(s"reference of selection1 ${selection1}")
  counter ! PoisonPill

  Thread.sleep(2000L)

  val counter2 = system.actorOf(Props[CounterActor], "counter")
  println(s"actor reference of counter2 ${counter2}")

  val selection2 = system.actorSelection("counter")
  println(s"reference of selection2 ${selection2}")
  counter2 ! PoisonPill

  Thread.sleep(2000L)

  system.terminate()
}

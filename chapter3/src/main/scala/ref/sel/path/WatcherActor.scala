package ref.sel.path

import akka.actor._


class WatcherActor extends Actor {

  var counterRef: ActorRef = _

  Thread.sleep(2000L)
  val counterRefSel = context.actorSelection("/user/counter")

  Thread.sleep(2000L)
  counterRefSel ! Identify(None)

  override def receive: Receive = {
    case ActorIdentity(_, Some(ref)) =>
      println(s"the reference of the actor is $ref")
    case ActorIdentity(_, None) =>
      println(s"the reference of the actor is none ")
    case msg =>
    counterRefSel ! Identify(None)
  }
}

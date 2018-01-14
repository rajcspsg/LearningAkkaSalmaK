package persistent.actor

import akka.actor._
import akka.persistence.{PersistentActor, SnapshotOffer}
import Counter._

object Counter {

  sealed trait Operation {
    val count: Int
  }

  case class Increment(override val count: Int) extends Operation
  case class Decrement(override val count: Int) extends Operation

  case class Cmd(op: Operation)
  case class Event(op: Operation)

  case class State(count: Int)
}


class Counter extends PersistentActor with ActorLogging {

  println(s"Starting...")

  //override def = "counter-example"

  var state: State = State(0)

  def updateState(evt: Event): Unit = {
    evt.op match {
      case Increment(count) =>
        state = State(state.count + count)
      case Decrement(count) =>
        state = State(state.count - count)
    }
  }

  override def receiveRecover: Receive = {
    case evt: Event =>
      updateState(evt)
    case SnapshotOffer(_, snaphot: State) =>
      state = snaphot
  }

  override def receiveCommand: Receive = {
    case cmd @ Cmd(op) =>
       println(s"counter received command $op")
       persist(Event(op)) { evt =>
         updateState(evt)
       }
    case "print" =>
      println(s"current state of the counter is $state")
  }

  override def persistenceId: String = "counter-example"
}

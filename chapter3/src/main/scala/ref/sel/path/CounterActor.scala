package ref.sel.path

import akka.actor.Actor
class CounterActor extends Actor {
  import CounterActor._
  
  var i = 0
  override def receive: Receive = {
    case Inc(num) =>  i = i + num
    case Dec(num) =>  i = i - num
  }

}

object CounterActor {
  case class Inc(num: Int)
  case class Dec(num: Int)
}

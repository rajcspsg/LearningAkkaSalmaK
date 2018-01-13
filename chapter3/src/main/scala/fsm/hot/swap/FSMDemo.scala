package fsm.hot.swap

import akka.actor._
import UserStorageFSM._
import DBState._
import FSMState._

object FSMDemo extends App {
  val system = ActorSystem("hotswap")
  val storage = system.actorOf(Props[UserStorageFSM], "storage")
  //storage ! Connect
  storage ! OperationMsg(DBOperation.Create, User("me", "me@gmail.com"))
  storage ! Disconnect

  storage ! Connect
  Thread.sleep(5000L)
  system.terminate()
}

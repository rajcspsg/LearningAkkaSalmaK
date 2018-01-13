package hot.swap

import akka.actor._
import UserStorage.DBState._

object HotSwapDemo extends App {
  import UserStorage._

  val system = ActorSystem("hotswap")
  val storage = system.actorOf(Props[UserStorage], "storage")
  //storage ! Connect
  storage ! OperationMsg(DBOperation.Create, User("me", "me@gmail.com"))
  storage ! Disconnect

  storage ! Connect
  Thread.sleep(5000L)
  system.terminate()
}

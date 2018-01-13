package hot.swap

import akka.actor._


class UserStorage extends Actor with Stash {

  import UserStorage._
  import DBState._

  override  def receive: Receive = disconnected

  def connected: Receive = {
    case Disconnect =>
      println(s"user Storage disconnect from database")
      context.unbecome()

    case OperationMsg(operation, user) =>
      println(s"operation ${operation} is done for user $user")
  }

  def disconnected: Receive = {
    case Connect =>
      println(s"User Storage connect from DB")
      unstashAll()
      context.become(connected)
    case _ =>
      stash()
  }


}

object  UserStorage {

  trait DBState
  object DBState {
    case object Connect extends DBState
    case object Disconnect extends DBState
  }

  trait DBOperation

  object DBOperation {
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case class User(name: String, mail: String)
  case class OperationMsg(dbOperation: DBOperation, user: User)

}

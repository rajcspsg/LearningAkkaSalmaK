package fsm.hot.swap

import akka.actor._
import UserStorageFSM._
import DBState._
import FSMState._

class UserStorageFSM extends FSM[FSMState, Data] with Stash {

  startWith(Disconnected, EmptyData)

  when(Disconnected) {
    case Event(Connect, _) =>
      println(s"UserStorage connected to Data")
      unstashAll()
      goto(Connected) using(EmptyData)

    case Event(_, _) =>
      stash()
      stay using(EmptyData)
  }

  when(Connected) {
    case Event(Disconnect, _) =>
      println(s"UserStorage Disconnected from DB")
      goto(Disconnected) using(EmptyData)

    case Event(OperationMsg(op, user), _) =>
      println(s"UserStorageFSM received operation $op for user $user")
      stay using(EmptyData)
  }

  initialize()

}

object  UserStorageFSM {

  trait FSMState
  object FSMState {
    case object Connected extends FSMState
    case object Disconnected extends FSMState
  }

  trait DBState
  object DBState {
    case object Connect extends DBState
    case object Disconnect extends DBState
  }

  sealed trait Data
  case object EmptyData extends Data

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

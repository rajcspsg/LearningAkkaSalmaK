package talking.to.actors

import akka.actor._

class Storage extends Actor {

  import Storage._

  var users = List.empty[User]

  override def receive: Receive = {
    case AddUser(user) =>
      users = user :: users
      println(s"current list is $users")
  }

}

object Storage {

  sealed trait StorageMsg
  case class AddUser(user: User) extends StorageMsg

}

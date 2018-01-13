package talking.to.actors

import akka.actor._

class Checker extends Actor {

import Checker._
val blackList = List(User("adam", "adam@mail.com"))

override def receive: Receive = {
  case CheckUser(user) =>
  if(blackList.contains(user))
    sender() ! BlackUser(user)
  else
    sender() ! WhiteUser(user)
  }
}

object Checker {

  sealed trait CheckerMsg
  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse
  case class BlackUser(user: User) extends CheckerResponse
  case class WhiteUser(user: User) extends CheckerResponse

}

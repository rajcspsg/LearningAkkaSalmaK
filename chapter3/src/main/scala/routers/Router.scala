package routers

import Worker.Work
import akka.actor._

class Router extends Actor {

  var routees: List[ActorRef] = _

  override def preStart(): Unit = {
    routees =  List.fill(5) (context.actorOf(Props[Worker]))
  }

  override def receive: Receive = {
    case Work =>
      routees(util.Random.nextInt() % (routees.length-1)) forward Work
  }
}

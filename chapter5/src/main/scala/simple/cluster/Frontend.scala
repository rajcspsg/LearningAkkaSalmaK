package simple.cluster

import akka.actor._
import com.typesafe.config.{Config, ConfigFactory}
import simple.cluster.Backend._

class Frontend extends Actor {

  var backends = IndexedSeq.empty[ActorRef]

  override def receive: Receive = {
    case x: Add if(backends.isEmpty) =>
      println(s"service unavailable, backend doesn't available")
    case x:Add =>
      println(s"I will forward the operation to backend")
      backends(util.Random.nextInt(backends.size)) forward x

    case BackendRegistration =>
      val senderActorRef = sender()
      if(!backends.contains(senderActorRef)) {
        backends :+= senderActorRef
        context watch senderActorRef
      }

    case Terminated(actorRef) =>
      backends = backends.filterNot(_ == actorRef )
  }

}
object Frontend {
  private var frontend: ActorRef = _

  def initiate() = {
    val config: Config = ConfigFactory.load().getConfig("Frontend")
   // println(s"${config.toString}")
    val system = ActorSystem("ClusterSystem", config)
    val frontend = system.actorOf(Props[Frontend], name = "frontend")
  }

  def getFrontend = frontend
}

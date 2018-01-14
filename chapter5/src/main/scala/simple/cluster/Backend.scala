package simple.cluster

import akka.actor._
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import com.typesafe._
import com.typesafe.config.ConfigFactory

class Backend extends Actor {
  import Backend._
  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case Add(num1, num2) =>
      println(s"I'm ${self} and I received Add operation")
    case MemberUp(member) =>
      if(member.hasRole("frontend")) {
        context.actorSelection(RootActorPath(member.address) / "user" / "frontend") ! BackendRegistration

      }
  }


}

object Backend {
  case class Add(num1: Int, num2: Int)
  case object BackendRegistration

  def initiate(port: Int) = {
    val config =
      ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").withFallback(ConfigFactory.load().getConfig("Backend"))

    val system = ActorSystem("ClusterSystem", config)
    val backendActor = system.actorOf(Props[Backend], name = "backend")
  }
}

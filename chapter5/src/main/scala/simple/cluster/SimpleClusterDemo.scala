package simple.cluster

import simple.cluster.Backend.Add

object SimpleClusterDemo extends App {
  Frontend.initiate()

  Backend.initiate(29990)
  Backend.initiate(29991)
  Backend.initiate(29992)

  Thread.sleep(5000L)

  if(Frontend.getFrontend == null) {
    println(s"Frontend.getFrontend is null")
  }
  Frontend.getFrontend ! Add(2, 4)
}

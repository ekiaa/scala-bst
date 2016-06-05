package ekiaa.scala.bst.examples

import com.typesafe.scalalogging.StrictLogging
import ekiaa.scala.bst.Node

object Example extends App with StrictLogging {

  logger.debug("Start")

  val tree = new Node[Integer, String](None, 10, "value10")
  tree.insert(10, "new_value10")
  tree.insert(5, "value5")
  tree.insert(15, "value15")

  logger.debug("Stop")

}

package ekiaa.scala.bst.examples

import com.typesafe.scalalogging.StrictLogging
import ekiaa.scala.bst.AVLTree

object Example extends App with StrictLogging {

  logger.debug("Start")

  val tree = new AVLTree[Int, String]

  val value15 = "value15"
  tree.insert(15, value15)
  val value10: String = "value10"
  tree.insert(10, value10)
  val value5 = "value5"
  tree.insert(5, value5)
  tree.insert(1, "1")
  tree.insert(2, "2")
  tree.insert(3, "3")
  tree.insert(4, "5")

  require(tree.get(5).contains(value5))
  require(tree.get(10).contains(value10))
  require(tree.get(15).contains(value15))

  logger.debug("Stop")

}

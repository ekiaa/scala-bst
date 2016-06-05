package ekiaa.scala.bst.examples

import com.typesafe.scalalogging.StrictLogging
import ekiaa.scala.bst.AVLTree

object Example extends App with StrictLogging {

  logger.debug("Start")

  val tree = new AVLTree[Int, String]
  val value10: String = "value10"
  tree.insert(10, value10)
  val value5 = "value5"
  tree.insert(5, value5)
  val value15 = "value15"
  tree.insert(15, value15)

  require(tree.get(5).contains(value5))
  require(tree.get(10).contains(value10))
  require(tree.get(15).contains(value15))

  require(tree.root.get.leftHeight == 1)
  require(tree.root.get.rightHeight == 1)

  logger.debug("Stop")

}

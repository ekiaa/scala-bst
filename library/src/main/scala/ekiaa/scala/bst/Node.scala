package ekiaa.scala.bst

import scala.annotation.tailrec

class Node[K <: Comparable[K], V](var parent: Option[Node[K, V]], key: K, value: V) {
  var leftHeight = 0
  var rightHeight = 0
  var left: Option[Node[K, V]] = None
  var right: Option[Node[K, V]] = None

  var nodeKey: K = key
  var nodeValue: V = value

  @tailrec
  final def insert(key: K, value: V): Unit = {
    if (nodeKey == key) {
      nodeValue = value
    } else {
      if (nodeKey.compareTo(key) < 0) {
        left match {
          case None =>
            left = Some(new Node[K, V](Some(this), key, value))
          case Some(node) =>
            node.insert(key, value)
        }
      } else {
        right match {
          case None =>
            right = Some(new Node[K, V](Some(this), key, value))
          case Some(node) =>
            node.insert(key, value)
        }
      }
    }
  }
}

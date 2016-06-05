package ekiaa.scala.bst

import scala.annotation.tailrec

class Node[K : Ordering, V](var parent: Option[Node[K, V]], key: K, value: V) {
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
      import Ordered._
      if (nodeKey < key) {
        left match {
          case None =>
            val node = new Node[K, V](Some(this), key, value)
            left = Some(node)
          case Some(node) =>
            node.insert(key, value)
        }
      } else {
        right match {
          case None =>
            val node = new Node[K, V](Some(this), key, value)
            right = Some(node)
          case Some(node) =>
            node.insert(key, value)
        }
      }
    }
  }

  @tailrec
  final def get(key: K): Option[V] = {
    if (nodeKey == key) {
      Some(nodeValue)
    } else {
      import Ordered._
      if (nodeKey < key) {
        left match {
          case None =>
            None
          case Some(node) =>
            node.get(key)
        }
      } else {
        right match {
          case None =>
            None
          case Some(node) =>
            node.get(key)
        }
      }
    }
  }
}

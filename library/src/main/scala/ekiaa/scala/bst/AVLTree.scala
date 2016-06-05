package ekiaa.scala.bst

import scala.annotation.tailrec

class AVLTree[K : Ordering, V] {

  var root: Option[AVLTree[K, V]] = None
  var nodeParent: Option[AVLTree[K, V]] = None

  var leftHeight: Int = 0
  var rightHeight: Int = 0

  var left: Option[AVLTree[K, V]] = None
  var right: Option[AVLTree[K, V]] = None

  var nodeKey: Option[K] = None
  var nodeValue: Option[V] = None

  private def this(parent: AVLTree[K, V]) = {
    this()
    nodeParent = Some(parent)
  }

  @tailrec
  final def insert(key: K, value: V): Unit = {
    nodeParent match {
      case None =>
        root match {
          case None =>
            val node = new AVLTree[K, V](this)
            root = Some(node)
            node.insert(key, value)
          case Some(node) =>
            node.insert(key, value)
        }
      case Some(parent) =>
        nodeKey match {
          case None =>
            nodeKey = Some(key)
            nodeValue = Some(value)
            parent.up(this)
          case Some(nkey) =>
            if (nkey == key) {
              nodeValue = Some(value)
            } else {
              import Ordered._
              if (nkey < key) {
                left match {
                  case None =>
                    val node = new AVLTree[K, V](this)
                    left = Some(node)
                    node.insert(key, value)
                  case Some(node) =>
                    node.insert(key, value)
                }
              } else {
                right match {
                  case None =>
                    val node = new AVLTree[K, V](this)
                    right = Some(node)
                    node.insert(key, value)
                  case Some(node) =>
                    node.insert(key, value)
                }
              }
            }
        }
    }
  }

  @tailrec
  final def get(key: K): Option[V] = {
    nodeParent match {
      case None =>
        root match {
          case None =>
            None
          case Some(node) =>
            node.get(key)
        }
      case Some(_) =>
        nodeKey match {
          case None =>
            None
          case Some(nkey) =>
            if (nkey == key) {
              nodeValue
            } else {
              import Ordered._
              if (nkey < key) {
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
  }

  @tailrec
  private def up(node: AVLTree[K, V]): Unit = {
    nodeParent match {
      case None =>
      case Some(parent) =>
        val height: Int = node.maxHeight
        if (left.contains(node)) {
          leftHeight = height + 1
        } else {
          if (right.contains(node)) {
            rightHeight = height + 1
          } else {
            throw new Exception("[up] Unexpected node reference")
          }
        }
        parent.up(this)
    }
  }

  private def maxHeight: Int = {
    if (leftHeight > rightHeight) {
      leftHeight
    } else {
      rightHeight
    }
  }
}

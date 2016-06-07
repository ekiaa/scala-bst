package ekiaa.scala.bst

import scala.annotation.tailrec

class AVLTree[K : Ordering, V] {

  private type Node = AVLTree[K, V]

  var root: Option[Node] = None
  var nodeParent: Option[Node] = None

  var leftHeight: Int = 0
  var rightHeight: Int = 0

  var left: Option[Node] = None
  var right: Option[Node] = None

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
            val node = new Node(this)
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
              if (nkey > key) {
                left match {
                  case None =>
                    val node = new Node(this)
                    left = Some(node)
                    node.insert(key, value)
                  case Some(node) =>
                    node.insert(key, value)
                }
              } else {
                right match {
                  case None =>
                    val node = new Node(this)
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
              if (nkey > key) {
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
  private def up(node: Node): Unit = {
    nodeParent match {
      case None =>
        checkHeightDifferenceRule(node)
      case Some(parent) =>
        val height: Int = node.maxHeight
        if (left.contains(node)) {
          leftHeight = height + 1
          checkLeftRightHeightRule(node)
          left.foreach(checkHeightDifferenceRule)
        } else {
          if (right.contains(node)) {
            rightHeight = height + 1
            checkLeftRightHeightRule(node)
            right.foreach(checkHeightDifferenceRule)
          } else {
            throw new Exception("[up] Unexpected node reference")
          }
        }
        parent.up(this)
    }
  }

  private def checkLeftRightHeightRule(node: Node): Unit = {
    nodeParent match {
      case None =>
      case Some(_) =>
        if (left.contains(node)) {
          if (node.rightHeight > node.leftHeight) {
            leftRotate(node)
          }
        } else {
          if (right.contains(node)) {
            if (node.leftHeight > node.rightHeight) {
              rightRotate(node)
            }
          } else {
            throw new Exception("[checkLeftRightHeightRule] Unexpected node reference")
          }
        }
    }
  }

  private def checkHeightDifferenceRule(node: Node): Unit = {
    if (node.leftHeight - node.rightHeight >= 2) {
      rightRotate(node)
    } else {
      if (node.rightHeight - node.leftHeight >= 2) {
        leftRotate(node)
      }
    }
  }

  private def rightRotate(p: Node): Unit = {
    nodeParent match {
      case None =>
        doRightRotate(p).foreach(q => { root = Some(q) })
      case Some(_) =>
        if (left.contains(p)) {
          doRightRotate(p).foreach(q => { left = Some(q) })
        } else {
          if (right.contains(p)) {
            doRightRotate(p).foreach(q => { right = Some(q) })
          } else {
            throw new Exception("[rightRotate] Unexpected node reference")
          }
        }
    }
  }

  private def doRightRotate(p: Node): Option[Node] = {
    p.left.map(q => {
      val bNode: Option[Node] = q.right
      bNode.foreach(b => { b.nodeParent = Some(p) })
      p.left = bNode
      p.nodeParent = Some(q)
      p.leftHeight -= 1
      q.right = Some(p)
      q.nodeParent = Some(this)
      q.rightHeight += 1
      q
    })
  }

  private def leftRotate(q: Node): Unit = {
    nodeParent match {
      case None =>
        doLeftRotate(q).foreach(p => { root = Some(p) })
      case Some(_) =>
        if (left.contains(q)) {
          doLeftRotate(q).foreach(p => { left = Some(p) })
        } else {
          if (right.contains(q)) {
            doLeftRotate(q).foreach(p => { right = Some(p) })
          } else {
            throw new Exception("[leftRotate] Unexpected node reference")
          }
        }
    }
  }

  private def doLeftRotate(q: Node): Option[Node] = {
    q.left.map(p => {
      val bNode: Option[Node] = p.left
      bNode.foreach(b => { b.nodeParent = Some(q) })
      q.left = bNode
      q.nodeParent = Some(q)
      q.rightHeight -= 1
      p.right = Some(q)
      p.nodeParent = Some(this)
      p.leftHeight += 1
      p
    })
  }

  private def maxHeight: Int = {
    if (leftHeight > rightHeight) {
      leftHeight
    } else {
      rightHeight
    }
  }
}

package ekiaa.scala.bst

import org.scalatest.{FlatSpec, Matchers}

class AVLTreeSpec extends FlatSpec with Matchers {

  "A simplest AVLTree" should "add root node and return value" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(1, "1")
    tree.get(1) shouldBe Some("1")
  }

  "An AVLTree" should "add left node" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(2, "2")
    tree.insert(1, "1")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
  }

  "An AVLTree" should "add right node" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(1, "1")
    tree.insert(2, "2")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
  }

  "An AVLTree" should "rotate to left when left node shifted to the right side" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(3, "3")
    tree.insert(4, "4")
    tree.insert(1, "1")
    tree.insert(2, "2")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
    tree.get(3) shouldBe Some("3")
    tree.get(4) shouldBe Some("4")
  }

  "An AVLTree" should "rotate to right when right node shifted to the left side" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(2, "2")
    tree.insert(1, "1")
    tree.insert(4, "4")
    tree.insert(3, "3")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
    tree.get(3) shouldBe Some("3")
    tree.get(4) shouldBe Some("4")
  }

  "An AVLTree" should "rotate to right when left more then right height by 2 or more" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(3, "3")
    tree.insert(2, "2")
    tree.insert(1, "1")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
    tree.get(3) shouldBe Some("3")
  }

  "An AVLTree" should "rotate to left when right more then left height by 2 or more" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(1, "1")
    tree.insert(2, "2")
    tree.insert(3, "3")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
    tree.get(3) shouldBe Some("3")
  }

  "An AVLTree" should "combine the first left and then right rotation" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(3, "3")
    tree.insert(1, "1")
    tree.insert(2, "2")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
    tree.get(3) shouldBe Some("3")
  }

  "An AVLTree" should "combine the first right and then left rotation" in {
    val tree = new AVLTree[Int, String]()
    tree.insert(1, "1")
    tree.insert(3, "3")
    tree.insert(2, "2")
    tree.get(1) shouldBe Some("1")
    tree.get(2) shouldBe Some("2")
    tree.get(3) shouldBe Some("3")
  }
}

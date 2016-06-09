package ekiaa.scala.bst.benchmarks

import ekiaa.scala.bst.AVLTree
import org.openjdk.jmh.annotations.Benchmark

/**
  * sbt
  * project library
  * jmh:run -f 0 -i 5 -bm avgt -tu ns
  */

class AVLTreeBenchmark {

  @Benchmark
  def baseline(): Unit = {
    val tree = new AVLTree[Int, Int]()
    tree.insert(1, 1)
  }

}

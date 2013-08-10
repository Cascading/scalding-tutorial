import com.twitter.scalding._
import com.twitter.scalding.mathematics.Matrix


/*
* MatrixTutorial3.scala
*
* Loads two directed graph adjacency matrices where a[i,j] = 1 if there is an edge from a[i] to b[j]
* and computes the intersection and the differences between the two
* 
* hadoop jar target/scalding-tutorial-0.8.8.jar MatrixTutorial3 --local\
* --input1 data/graph.tsv --input2 data/graph2.tsv --intersection data/intersection.tsv --leftDiff target/data/leftDiff.tsv --rightDiff target/data/rightDiff.tsv
*
*/


class MatrixTutorial3(args : Args) extends Job(args) {
 
  import Matrix._
  
  val adjacencyMatrix1 = Tsv( args("input1"), ('user1, 'user2, 'rel) )
    .read
    .toMatrix[Long,Long,Double]('user1, 'user2, 'rel)

  val adjacencyMatrix2 = Tsv( args("input2"), ('user1, 'user2, 'rel) )
    .read
    .toMatrix[Long,Long,Double]('user1, 'user2, 'rel)

  //zip puts creates a pair element out of corresponding elements in the two matrices
  val intersection = adjacencyMatrix1
                        .zip(adjacencyMatrix2)
                        .mapValues( pair => if (pair._1 > 0 && pair._2 > 0) 1.0 else 0.0 )
                        .write(Tsv(args("intersection")))
  (adjacencyMatrix1 - intersection).write(Tsv(args("leftDiff")))
  (adjacencyMatrix2 - intersection).write(Tsv(args("rightDiff")))
  
}


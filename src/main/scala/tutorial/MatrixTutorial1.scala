import com.twitter.scalding._
import com.twitter.scalding.mathematics.Matrix


/*
* MatrixTutorial1.scala
* 
* Loads a directed graph adjacency matrix where a[i,j] = 1 if there is an edge from a[i] to b[j]
* and compute the co-follows between any two nodes 
*
  yarn jar target/scalding-tutorial-0.14.0.jar MatrixTutorial1 --local\
    --input data/graph.tsv --output target/data/cofollows.tsv
*
*/


class MatrixTutorial1(args : Args) extends Job(args) {
  
  import Matrix._
  
  val adjacencyMatrix = Tsv( args("input"), ('user1, 'user2, 'rel) )
  	.read
  	.toMatrix[Long,Long,Double]('user1, 'user2, 'rel)

  // compute the innerproduct of the adjacency matrix with itself 
  (adjacencyMatrix * adjacencyMatrix.transpose).write( Tsv( args("output") ) )
}


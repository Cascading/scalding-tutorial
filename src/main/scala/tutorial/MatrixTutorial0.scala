import com.twitter.scalding._
import com.twitter.scalding.mathematics.Matrix

/*
* MatrixTutorial0.scala
*
* Loads a directed graph adjacency matrix where a[i,j] = 1 if there is an edge from a[i] to b[j]
* and compute the outdegree of each node i
*
* hadoop jar target/scalding-tutorial-project-0.8.5.jar --local MatrixTutorial0 \
* --input data/graph.tsv \
* --output target/data/outdegree.tsv
*
*/


class MatrixTutorial0(args : Args) extends Job(args) {

  import Matrix._

  val adjacencyMatrix = Tsv( args("input"), ('user1, 'user2, 'rel) )
    .read
    .toMatrix[Long,Long,Double]('user1, 'user2, 'rel)

  // each row i represents all of the outgoing edges from i
  // by summing out all of the columns we get the outdegree of i
  adjacencyMatrix.sumColVectors.write( Tsv( args("output") ) )
}


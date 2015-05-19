import com.twitter.scalding._
import com.twitter.scalding.mathematics.Matrix


/*
* MatrixTutorial2.scala
* 
* Loads a directed graph adjacency matrix where a[i,j] = 1 if there is an edge from a[i] to b[j]
* and returns a graph containing only the nodes with outdegree smaller than a given value
* 
  yarn jar target/scalding-tutorial-0.14.0.jar MatrixTutorial2 --local\
    --input data/graph.tsv --maxOutdegree 1000 --output target/data/graphFiltered.tsv
* 
*/


class MatrixTutorial2(args : Args) extends Job(args) {
  
  import Matrix._
    
  val adjacencyMatrix = Tsv( args("input"), ('user1, 'user2, 'rel)  )
    .read
    .toMatrix[Long,Long,Double]('user1, 'user2, 'rel)

  // Each row corresponds to the outgoing edges so to compute the outdegree we sum out the columns 
  val outdegree = adjacencyMatrix.sumColVectors

  // We convert the column vector to a matrix object to be able to use the matrix method filterValues
  // we make all non zero values into ones and then convert it back to column vector
  val outdegreeFiltered = outdegree.toMatrix[Int](1)
                          .filterValues{ _ < args("maxOutdegree").toDouble }
                          .binarizeAs[Double].getCol(1)
						           				           
  // We multiply on the left hand side with the diagonal matrix created from the column vector
  // to keep only the rows with outdregree smaller than maxOutdegree
  (outdegreeFiltered.diag * adjacencyMatrix).write(Tsv( args("output") ) )

}


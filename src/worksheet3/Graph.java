package worksheet3;

import java.util.Iterator;

public class Graph {
    private AdjacencyMatrix neighboursMatrix;
    private AdjacencyLists neighboursLists;
    public Graph(int[][] adjMatrix){
        neighboursMatrix = new AdjacencyMatrix(adjMatrix);
        neighboursLists = new AdjacencyLists(neighboursMatrix);
    }
    public AdjacencyList getNeighboursFor(int v){
        return neighboursLists.getNeighboursFor(v);
    }

    public int numOfVertices() {
        return neighboursMatrix.numOfVertices();
    }
    public int numOfEdges(){
        int num = 0;
        for(int i = 0; i < numOfVertices(); i++){
            AdjacencyList tmp = neighboursLists.getNeighboursFor(i);
            Iterator<Integer> iterator = tmp.iterator();
            while (iterator.hasNext()){
                if (iterator.next() == 1){
                    num++;
                }
            }
        }
        return num;
    }
    public int getWeight(int u, int v){
        return neighboursMatrix.get(u, v);
    }
    public AdjacencyList somePath(int v, int lenght){
        return neighboursLists.getNeighboursFor(v);//todo
    }
}

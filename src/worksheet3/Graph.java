package worksheet3;

import java.util.ArrayList;
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
                iterator.next();
                num++;
            }
        }
        return num;
    }
    public int getWeight(int u, int v){
        return neighboursMatrix.get(u, v);
    }
    public AdjacencyList somePath(int v, int length){
        AdjacencyList adjacencyList = new AdjacencyList(v);
        int currentVertex = v;
        int totalVertices = 0;
        int lastVertex = 0;
        while(totalVertices < length){
            Iterator<Integer> iterator = neighboursLists.getNeighboursFor(currentVertex).iterator();
            int repetitions = 0;
            if(!iterator.hasNext()){
                return adjacencyList;
            }
            while(iterator.hasNext()){
                int vertex = iterator.next();
                if(repetitions>0){
                    return adjacencyList;
                }
                if(adjacencyList.contains(vertex)){
                    repetitions++;
                    continue;
                } else {
                    repetitions = 0;
                }
                lastVertex = vertex;
                adjacencyList.add(vertex);
                totalVertices++;
            }
            currentVertex = lastVertex;
        }
        return adjacencyList;
    }
}

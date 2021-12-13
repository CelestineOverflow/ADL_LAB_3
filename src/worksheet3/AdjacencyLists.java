package worksheet3;

import java.util.ArrayList;

public class AdjacencyLists {
    private ArrayList<AdjacencyList> neighbourList;
    public AdjacencyLists(AdjacencyMatrix neighboursMatrix){
        neighbourList = new ArrayList<>();
        for(int y = 0; y < neighboursMatrix.numOfVertices(); y++){
            AdjacencyList temp = new AdjacencyList(y);
            for(int x = 0; x < neighboursMatrix.numOfVertices(); x++){
                if(neighboursMatrix.get(y, x) == 1){
                    temp.add(x);
                }
            }
            neighbourList.add(temp);
        }
    }
    public AdjacencyList getNeighboursFor(int v){
        return neighbourList.get(v);
    }
}

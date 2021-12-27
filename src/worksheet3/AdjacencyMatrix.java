package worksheet3;

public class AdjacencyMatrix {
    private int[][] adjMatrix;

    public AdjacencyMatrix(int[][] adjMatrix){
        this.adjMatrix =  adjMatrix;
    }

    public int get(int v, int u){
        return adjMatrix[v][u];
    }

    public int numOfVertices(){
        return adjMatrix[1].length; // if broken use this instead: adjMatrix[0].length
    }
}

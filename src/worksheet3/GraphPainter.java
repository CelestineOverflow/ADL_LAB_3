/*
 * B-REE3-VSP HAW Hamburg
 *
 * Created on : 10-12-2020
 * Author     : Björn Gottfried
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | B. Gottfried  | Initial Create.
 *         | 10-12-20      |
 *---------|---------------|---------------------------------------------------
 *         | author        |
 *         | dd-mm-yy      |
 *---------|---------------|---------------------------------------------------
 */

package worksheet3;

import java.awt.*;
import java.util.Iterator;
import javax.swing.JFrame;

public class GraphPainter extends Canvas {
    //Size
    private static Dimension DEFAULT_WINDOWS_SIZE = new Dimension(1600 , 800);
    private static int DEFAULT_VERTEX_SIZE = 40;
    //Colors
    private static Color VERTEX_COLOR = new Color(121, 0, 59);
    private static Color WINDOWS_BACKGROUND_COLOR = new Color(65, 92, 69);
    private static final long serialVersionUID = 1L;
    private Dimension screenSize;

    private Graph aGraph;
    private AdjacencyList aPath;

    public GraphPainter(Graph g){
        this.aGraph = g;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("A graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        setSize(DEFAULT_WINDOWS_SIZE);
        setBackground(WINDOWS_BACKGROUND_COLOR);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void setAPath(AdjacencyList l) {
        aPath = l;
    }

    /********************************************************************************
     * Painting the Graph on the screen
     ********************************************************************************/

    public void paint(Graphics g) {
        int[][] positionsOfVertices = layoutOfGraph();
        drawEdges(g, positionsOfVertices);
        drawVertices(g, positionsOfVertices);

    }

    private void drawVertices(Graphics g, int[][] positions){
        g.setColor(Color.black);
        for (int v = 0; v < aGraph.numOfVertices(); v++) {
            g.setColor(VERTEX_COLOR);
            g.fillOval(positions[v][0], positions[v][1], 50, 50);
            g.setColor(Color.BLACK);
            g.drawOval(positions[v][0], positions[v][1], 50, 50);
            g.setFont(new Font("Helvetica", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
            g.drawString(""+v, positions[v][0]+20, positions[v][1]+32);
        }
    }

    private void drawEdges(Graphics g, int[][] positions){
        g.setColor(Color.blue);
        for (int u = 0; u < aGraph.numOfVertices(); u++) {
            for (int v = u + 1; v < aGraph.numOfVertices(); v++) {
                if (aGraph.getWeight(u, v) != 0) {
                    if (aPath != null && aPath.contains(v)) {
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.blue);
                    }
                    int x1, x2, y1, y2;
                    x1 = positions[u][0]+25;
                    y1 = positions[u][1]+25;
                    x2 = positions[v][0]+25;
                    y2 = positions[v][1]+25;
                    if(x1 == x2 && y1 == y2){
                        drawSelfRef(g, x1, y1);
                    } else{
                        g.drawLine(
                                x1, y1,
                                x2, y2);
                    }
                }
            }
        }
    }
    private void drawSelfRef(Graphics g, int x, int y){
        g.drawArc(x, y, 100, 100, 0, 360);
    }

    private int[][] layoutOfGraph(){
        int xMin = DEFAULT_WINDOWS_SIZE.width / 2;
        return new int[][] {
                {xMin +   0, 100},
                {xMin + 200, 200},
                {xMin + 400, 400},
                {xMin + 200, 600},
                {xMin -   0, 700},
                {xMin - 200, 600},
                {xMin - 400, 400},
                {xMin - 200, 200}};
    }

    /********************************************************************************
     * Testprogram
     ********************************************************************************/

    public static void main(String[] args) {
        final int H = 0;
        int[][] adjMatrix = {//first
                {H, 0, 0, 1, 1, 0, 0, 0}, // vertice 0 has two neighbours 3 and 4
                {0, H, 1, 0, 0, 0, 1, 0},
                {0, 0, H, 0, 0, 0, 0, 1},
                {0, 0, 0, H, 1, 0, 0, 1},
                {0, 0, 0, 0, H, 1, 0, 1},
                {0, 0, 0, 0, 0, H, 1, 1},
                {0, 0, 0, 0, 0, 0, H, 1},
                {0, 0, 0, 0, 0, 0, 0, H}};

        Graph g = new Graph(adjMatrix);

        GraphPainter painter = new GraphPainter(g);

        AdjacencyList aPath = g.somePath(0, 3);
        painter.setAPath(aPath);

        // Print all the vertices and their neighbours
        for (int v = 0; v < g.numOfVertices(); v++) {
            System.out.print("Number Of Neighbours "+v+":");
            AdjacencyList adjList = g.getNeighboursFor(v);
            //this wont work
            //for (Integer neighbour: adjList) {
            //    System.out.print(" " + neighbour.toString());
            //}
            Iterator iterator = adjList.iterator();
            while(iterator.hasNext()){
                System.out.print(" " + String.valueOf(iterator.next()));
            }
            System.out.println();
        }
        System.out.println("Number Of Edges: "+g.numOfEdges());
    }
}
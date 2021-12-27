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
 *         | C. S. Machuca | New Graphics.
 *         | 14-12-2021    |
 *---------|---------------|---------------------------------------------------
 */

package worksheet3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;

public class GraphPainter extends Canvas {
    //Size
    private static final Dimension DEFAULT_WINDOWS_SIZE = new Dimension(1600 , 800);
    private static final int DEFAULT_VERTEX_SIZE = 50;
    //Colors
    private static final Color VERTEX_COLOR = new Color(0, 85, 77);
    private static final Color WINDOWS_BACKGROUND_COLOR = new Color(50, 50, 50);
    private static final long serialVersionUID = 1L;
    private final Dimension screenSize;

    private final Graph aGraph;
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
        ArrayList<Point> positions = circularLayout(aGraph.numOfVertices());
        drawEdges(g, positions);
        drawVertices(g, positions);
    }

    private void drawVertices(Graphics g, ArrayList<Point> points){
        int current = 0;
        for(Point point : points){
            g.setColor(VERTEX_COLOR);
            Point newLocation = new Point(point.x - DEFAULT_VERTEX_SIZE/2, point.y -DEFAULT_VERTEX_SIZE/2);
            g.fillOval(newLocation.x , newLocation.y, DEFAULT_VERTEX_SIZE, DEFAULT_VERTEX_SIZE);
            g.setColor(Color.BLACK);
            g.drawOval(newLocation.x, newLocation.y, DEFAULT_VERTEX_SIZE, DEFAULT_VERTEX_SIZE);
            g.setFont(new Font("Helvetica", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(current), point.x-5, point.y+5);
            current++;
        }
    }

    private void drawEdges(Graphics g, ArrayList<Point> points){
        g.setColor(Color.BLUE);
        for (int u = 0; u < aGraph.numOfVertices(); u++) {
            for (int v = u + 1; v < aGraph.numOfVertices(); v++) {
                if (aGraph.getWeight(u, v) != 0) {
                    if (aPath != null && aPath.contains(u) && aPath.contains(v)) {
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.blue);
                    }
                    int x1, x2, y1, y2;
                    x1 = points.get(u).x;
                    y1 = points.get(u).y;
                    x2 = points.get(v).x;
                    y2 = points.get(v).y;
                    g.drawLine(x1, y1,x2, y2);
                }
            }
        }
    }
    private void drawCenter(Graphics g){
        Point center = new Point(screenSize.width/2, screenSize.height/2);
        g.setColor(Color.GREEN);
        g.drawLine(center.x-10, center.y, center.x+10, center.y);
        g.drawLine(center.x, center.y-10, center.x, center.y+10);
    }
    private void drawCenter(Graphics g, int x, int y){
        Point center = new Point(x, y);
        g.setColor(Color.GREEN);
        g.drawLine(center.x-10, center.y, center.x+10, center.y);
        g.drawLine(center.x, center.y-10, center.x, center.y+10);
    }
    private ArrayList<Point> circularLayout(int numOfVertex){
        ArrayList<Point> points = new ArrayList<>();
        Point center = new Point(screenSize.width/2, screenSize.height/2);
        int vector_x = 0;
        int vector_y = -200;
        double angle = 360/numOfVertex;
        for (int i = 0; i < numOfVertex; i++){
            double currentAngle = angle*i;
            double cos = Math.cos(Math.toRadians(currentAngle));
            double sin = Math.sin(Math.toRadians(currentAngle));
            int tempX = (int) ((cos * vector_x)-(sin * vector_y));
            int tempY = (int) ((sin * vector_x)+(cos * vector_y));
            points.add(new Point(center.x + tempX, center.y+tempY));
        }
        return points;
    }
    private ArrayList<Point> squareLayout(int numOfVertex){
        ArrayList<Point> points = new ArrayList<>();
        int offset = screenSize.width/numOfVertex;
        int rows, columns;
        rows = numOfVertex/2;
        columns = 2;
        int currentX = 0;
        int currentY = 0;
        for(int y = 0; y < columns; y++){
            currentY += offset;
            for (int x = 0; x < rows; x++){
                currentX+=offset;
                points.add(new Point(currentX, currentY));
            }
            currentX = 0;
        }
        return points;
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

        AdjacencyList aPath = g.somePath(0, 10);
        painter.setAPath(aPath);
        // print Path adjList
        System.out.printf("A path: ");
        Iterator aPathIterator = aPath.iterator();
        while(aPathIterator.hasNext()){
            System.out.printf("%d -> ", aPathIterator.next());
        }
        System.out.printf("\n");
        // Print all the vertices and their neighbours
        for (int v = 0; v < g.numOfVertices(); v++) {
            System.out.print("Number Of Neighbours "+v+":");
            AdjacencyList adjList = g.getNeighboursFor(v);
            //this wont work
            for (Integer neighbour: adjList) {
                System.out.print(" " + neighbour.toString());
            }
            System.out.println();
        }
        System.out.println("Number Of Edges: "+g.numOfEdges());
    }
}
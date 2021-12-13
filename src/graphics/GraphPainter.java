package graphics;

import javax.swing.*;
import java.awt.*;

public class GraphPainter extends JPanel {
    GraphPainter(){
        setPreferredSize(MainWindows.DEFAULT_SCREEN_SIZE);
        setBackground(Color.red);
    }
    public void paintComponent(Graphics g){
        Pen.set(g);
        Graphics2D pen = Pen.get();
        draw();
    }
    public void draw(){
        Point center = new Point(MainWindows.DEFAULT_SCREEN_SIZE.width/2, MainWindows.DEFAULT_SCREEN_SIZE.height/2);
        Vertex vertex = new Vertex(center.x, center.y, 1, 0);
        vertex.draw();
    }
}

package graphics;

import java.awt.*;

public class Vertex {
    private Point position;
    private int size;
    private int DEFAULT_SIZE = 40;
    private Color DEFAULT_FILL_COLOR = new  Color(121, 0, 59);
    private Color DEFAULT_BORDER_COLOR = new  Color(121, 0, 59);
    private Font DEFAULT_FONT = new Font("Helvetica", Font.PLAIN, 20);
    private int id;
    public Vertex(int x, int y, double scale, int id){
        position = new Point(x, y);
        this.size = (int) (DEFAULT_SIZE * scale);
        this.id = id;
    }
    public void draw(){
        Graphics2D pen = Pen.get();
        pen.setColor(DEFAULT_FILL_COLOR);
        pen.fillOval(position.x, position.y, size, size);
        pen.setColor(DEFAULT_BORDER_COLOR);
        pen.drawOval(position.x, position.y, size, size);
        pen.drawString(String.valueOf(id), position.x, position.y);
    }
}

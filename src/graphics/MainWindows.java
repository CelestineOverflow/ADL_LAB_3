package graphics;

import javax.swing.*;
import java.awt.*;

public class MainWindows extends JFrame {
    public static Dimension DEFAULT_SCREEN_SIZE = new Dimension(800, 800);
    public MainWindows(){
        super();
        //BOILER PLATE CODE
        setSize(DEFAULT_SCREEN_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        //ADD CANVAS
        GraphPainter graphPainter = new GraphPainter();
        add(graphPainter);
        pack();
    }

    public static void main(String[] args) {
        MainWindows graphPainter = new MainWindows();
    }
}

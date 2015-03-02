
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class FuckYeaSistemi extends JFrame {

    Item[] sistema;
    float dt;
    static int width = 500;
    static int height = 500;
    static int delay = 50;
    
    public FuckYeaSistemi() {
        sistema = new Item[0];
    }

    public void paint(Graphics g) {
        float zoom = 4f;
        g.clearRect(0, 0, getWidth(), getHeight());
        for (Item a : sistema) {
            g.fillOval((int) (zoom * a.x + getWidth() / 2),
                    (int) (zoom * a.y + getHeight() / 2), 10, 10);
        }
        for (Item a : sistema) {
            a.tick(sistema, dt);
        }
    }

    public static void main(String[] args) {
        final FuckYeaSistemi frame = new FuckYeaSistemi();
        frame.setSize(width, height);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
            }
        }, 0, delay); // Repaint (= tick the simulation) every "delay" ms
    }
}

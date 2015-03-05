
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class FuckYeaSistemi extends JFrame {

    ArrayList<Item> sistema; // The system at the present state
    ArrayList<Item> births, deaths; // Scheduled births and deaths
    float dt;       // The simulation step (previous state was "t", next is "t+dt")

    static int width = 500;  // Window size
    static int height = 500;

    static int delay = 50; // The real-life delay between one step and another
    
    public FuckYeaSistemi() { // Spawn the initial items
        sistema = new ArrayList<Item>();
        sistema.add(new WaterSource(this, 0, 0));
        sistema.add(new Prey(this, 10, 10));
        births = new ArrayList<Item>();
    }

    public void scheduleBirth(Item i) {
        this.births.add(i);
    }
    
    void spawn(Item i) {
        sistema.add(i);
    }
    
    public void paint(Graphics g) { // Redraw the items
        float zoom = 4f;
        float dt = 0.01f;
        g.clearRect(0, 0, getWidth(), getHeight()); // Clear the whole window
        for (Item a : sistema) {
            g.setColor(a.color);
            g.fillOval((int) (zoom * a.x + getWidth() / 2),
                       (int) (zoom * a.y + getHeight() / 2), 10, 10);
        }
        for (Item a : sistema) {
            a.velocityTick(sistema, dt); // Do a "velocity tick" on each item, to update its velocity and stats
        }
        for (Item a : sistema) {
            a.positionTick(dt);          // Do a "position tick" on each item.
            // Note: should probably be inlined, since the behaviour can't be customized
        }
        
        for (Item a : births) {
            this.spawn(a);
        }
        births.clear();
    }

    public static void main(String[] args) {
        final FuckYeaSistemi frame = new FuckYeaSistemi(); // I hate you Java
        frame.setSize(width, height);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Exit upon closing the window
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

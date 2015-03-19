
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFrame;
import static org.lwjgl.opengl.GL11.*;
import java.nio.*; import java.io.*;

public class Simulazione extends JFrame {

    ArrayList<Item> sistema; // The system at the present state
    ArrayList<Item> deaths;  // Items to be killed
    ArrayList<Item> births;  // Items to be spawned
    float dt;       // The simulation step (previous state was "t", next is "t+dt")

    public Simulazione() { // Spawn the initial items
        sistema = new ArrayList<Item>();
        deaths = new ArrayList<Item>();
        births = new ArrayList<Item>();
        sistema.add(new WaterSource(this, 100, 100));
        sistema.add(new WaterSource(this, 200, 50));          sistema.add(new WaterSource(this, 50, 200));
        sistema.add(new Prey(this, 80, 80));
        sistema.add(new Predator(this, 180, 180));
        sistema.add(new Prey(this, 140, 140));
    }

    public void tick(OutputStream out) { // Redraw the items
        float dt = 0.01f;
        final float size = 20;
        for (Item a : sistema) {
            glBegin(GL_QUADS);
            glColor3f(a.r, a.g, a.b);
            glVertex3f(a.x - size / 2, a.y - size / 2, 0f);
            glVertex3f(a.x - size / 2, a.y + size / 2, 0f);
            glVertex3f(a.x + size / 2, a.y + size / 2, 0f);
            glVertex3f(a.x + size / 2, a.y - size / 2, 0f);
            glEnd();
        }
        for (Item a : sistema) {
            a.velocityTick(sistema, dt); // Do a "velocity tick" on each item, to update its velocity and stats
        }

        HashMap<String, Integer> stats = new HashMap<String, Integer>();


        for (Item a : sistema) {
            a.positionTick(dt);          // Do a "position tick" on each item.
/*            try {
                stats.put(a.getClass().getName(), stats.get(a.getClass().getName()) + 1);
            } catch (Exception e) {
                stats.put(a.getClass().getName(), 1);
            }
*/
        }
/*

        ArrayList<String> entries = new ArrayList<String>();
        for (Map.Entry pair : stats.entrySet()) {
            // <code>
            entries.add(String.valueOf(pair.getValue()));
            // </code>
        }
        
        // I know, this sucks :c
        String[] entriesArray = new String[entries.size()];
        entries.toArray(entriesArray);
        writer.writeNext(entriesArray);
*/

        for (Item a : deaths) {
            despawn(a);
        }
        deaths.clear();

        for (Item a : births) {
            spawn(a);
        }
        births.clear();
    }

    public void fikifiki(Item i) {
        births.add(i);
    }

    public void kill(Item i) {
        deaths.add(i);
    }

    public void kill(Item i, String reason) {
        System.out.println("A " + i.getClass().getName() + " died, reason: " + reason);
        deaths.add(i);
    }

    private void spawn(Item i) {
        sistema.add(i);
    }

    private void despawn(Item i) {
        sistema.remove(i);
    }
}

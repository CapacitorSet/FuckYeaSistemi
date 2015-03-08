
import java.util.ArrayList;
import javax.swing.JFrame;
import static org.lwjgl.opengl.GL11.*;

public class Simulazione extends JFrame {

    ArrayList<Item> sistema; // The system at the present state
	ArrayList<Item> deaths;  // Items to be killed
    float dt;       // The simulation step (previous state was "t", next is "t+dt")

    public Simulazione() { // Spawn the initial items
        sistema = new ArrayList<Item>();
		deaths = new ArrayList<Item>();
        sistema.add(new WaterSource(this, 100, 100));
        sistema.add(new WaterSource(this, 200, 50));
        sistema.add(new Prey(this, 80, 80));
		sistema.add(new Predator(this, 180, 180));
    }
    
    public void tick() { // Redraw the items
        float dt = 0.01f;
		final float size = 20;
        for (Item a : sistema) {
            glBegin(GL_QUADS);
            glColor3f(a.r, a.g, a.b);
            glVertex3f(a.x-size/2, a.y-size/2, 0f);
            glVertex3f(a.x-size/2, a.y+size/2, 0f);
            glVertex3f(a.x+size/2, a.y+size/2, 0f);
            glVertex3f(a.x+size/2, a.y-size/2, 0f);
            glEnd();
        }
        for (Item a : sistema) {
            a.velocityTick(sistema, dt); // Do a "velocity tick" on each item, to update its velocity and stats
        }
        for (Item a : sistema) {
            a.positionTick(dt);          // Do a "position tick" on each item.
            // Note: should probably be inlined, since the behaviour can't be customized
        }

		for (Item a : deaths) {
			despawn(a);
		}
		deaths.clear();
    }

	public void kill(Item i) {
		deaths.add(i);
	}

	private void despawn(Item i) {
		sistema.remove(i);
	}
}

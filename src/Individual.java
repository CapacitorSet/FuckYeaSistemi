/* public abstract Individual
 * Provides a set of features for Individuals (compute acceleration, do a velocity tick)
 * and defines some functions an Individual (eg. Preys, Predators) must implement:
 * loadData(), interactWith(Item i)
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Individual extends GravityItem {

    Random random;
	public Map<String, Integer> factor; // Each thing is mapped to its "weight" (attractiveness), eg. "WaterSource" -> 1000

	abstract void loadData();

    Individual(Simulazione simulazione, float x, float y) { // Spawn an individual with the default values
        random = new Random();
        this.simulazione = simulazione;
        this.r = 0;
		this.g = 1;
		this.b = 0;
        this.x = x;
        this.y = y;
        this.maxSpeed = 50;
		this.factor = new HashMap<String, Integer>();
		loadData();
    }

    // Compute the total acceleration at the current instant
    public final Vector totalAcceleration(List<Item> system) {
        Vector result = new Vector();
        // Loop through all the items other than oneself
        for (Item a : system) {
            if (a != this) {
				String name = a.getClass().getName();
				if (factor.containsKey(name)) { // If the object is in the weights map
					interactWith(a);            // Call interactWith() so that one can react (eg. drink if close to WaterSources)
					result.add(accelerationTo(a, factor.get(name))); // Compute the acceleration towards item a
                } else {
                    // Do nothing, object not identified. Feel neither attracted nor pushed away
                }
            }
        }
        return result;
    }

	abstract void interactWith(Item a);

    @Override
    public final void velocityTick(List<Item> sistema, float dt) {
        // Do the obvious stuff: on each instant, the velocity increases by dt*acceleration
        Vector acceleration = totalAcceleration(sistema);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;

        limitSpeed();

        this.customTick(); // Fire a custom tick to account for stuff like thirst, hunger, etc
    }

    public abstract void customTick();

    @Override
    public final void positionTick(float dt) { // Updates an individual's position
        this.x += dt * this.xVelocity;
        // Make sure the individual does not escape the game field
		if (this.x < 0) this.x = 0;
		if (this.x > Graphics.width) this.x = Graphics.width;

        this.y += dt * this.yVelocity;
		if (this.y < 0) this.y = 0;
		if (this.y > Graphics.height) this.y = Graphics.height;
    }

	public final void die(String reason) {
		simulazione.kill(this, reason);
	}

	public final void die() {
		simulazione.kill(this);
	}

}
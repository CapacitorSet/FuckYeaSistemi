import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Individual extends GravityItem {

    Random random;
	public Map<String, Integer> factor; // Hashmap of factors for each thing

	abstract void loadData();

    Individual(FuckYeaSistemi simulazione, float x, float y) { // Spawn an individual with the default values
        random = new Random();
        this.simulazione = simulazione;
        this.color = Color.GREEN;
        this.x = x;
        this.y = y;
        this.maxSpeed = 50;
		this.factor = new HashMap<>();
		loadData();
    }

    public final Vector totalAcceleration(List<Item> system) {
        Vector result = new Vector();
        for (Item a : system) {
            if (a != this) {
				String name = a.getClass().getName();
				if (factor.containsKey(name)) {
					this.interactWith(a);
					result.add(accelerationTo(a, factor.get(name)));
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

        this.customTick();
    }

    public abstract void customTick();

    @Override
    public final void positionTick(float dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }

}
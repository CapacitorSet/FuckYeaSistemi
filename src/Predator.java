/* Predator
 * Red, has thirst and hunger (hunts Preys)
 */

import java.util.Random;

public class Predator extends Individual {

	int thirst;
	int hunger;
      Random random;

	Predator(Simulazione simulazione, float x, float y) {
		super(simulazione, x, y);
	}

	@Override
	void loadData() {
		this.r = 1;
		this.g = 0;
		this.b = 0;
		this.maxSpeed = 50;
		this.thirst = 0;
		this.hunger = 0;
           this.random = new Random();
		factor.put("WaterSource", thirst);
		factor.put("Prey", hunger);
	}

	@Override
	public void customTick() {
		thirst += 1; // Increase the "thirst" (attraction to water sources)
		hunger += 1;

		if (thirst > 1000) die("Thirst");
		if (hunger > 1000) die("Hunger");

		/* If the prey is no longer thirsty, its attraction to water sources is zero (no effect whatsoever),
		 * not negative (actively escaping from water sources).
		 */
		factor.put("WaterSource", (thirst > 0 ? thirst : 0));
		factor.put("Prey", (hunger > 0 ? hunger : 100));

		if (random.nextInt(2100)==0) {
			simulazione.fikifiki(new Predator(simulazione, x, y));
		}
	}

public void interactWith(WaterSource w) {
System.out.println("a");
}

	public void interactWith(Item i) {
		if (i instanceof WaterSource && distanceTo(i) <= 10 && thirst > 0) {
			i.beConsumed();
			// If close to a water source, drink!
			thirst = -1000;
		}
		if (i instanceof Prey && distanceTo(i) <= 10) {
			// If close to a prey, eat it
			simulazione.kill(i, "Eaten");
			hunger = -2000;
		}
	}
    
public void beConsumed() {
        return;
    }
}

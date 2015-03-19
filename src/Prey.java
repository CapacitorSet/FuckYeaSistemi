/* Prey
 * Green, has thirst, escapes from Predators
 */

import java.util.Random;

public class Prey extends Individual {

	int thirst;
	Random random;

	Prey(Simulazione simulazione, float x, float y) {
		super(simulazione, x, y);
	}

	@Override
	void loadData() {
		this.r = 0;
		this.g = 1;
		this.b = 0;
		this.maxSpeed = 70;
		this.thirst = 0;
		this.random = new Random();
		factor.put("WaterSource", thirst);
		factor.put("Predator", -100);
	}

	@Override
	public void customTick() {
		thirst += 2; // Increase the "thirst" (attraction to water sources)

		if (thirst > 1000) die("Thirst");

		/* If the prey is no longer thirsty, its attraction to water sources is zero (no effect whatsoever),
		 * not negative (actively escaping from water sources).
		 */
		factor.put("WaterSource", (thirst > 0 ? thirst : 0));

		if (random.nextInt(750)==0) {
			simulazione.fikifiki(new Prey(simulazione, x, y));
		}
	}

	public void interactWith(Item i) {
		if (i instanceof WaterSource && distanceTo(i) <= 10 && ((WaterSource) i).amount > 0 && thirst > 0) {
            i.beConsumed();
			// If close to a water source, drink!
			thirst = -100;
		}
	}

    public void beConsumed() {
        return;
    }
}

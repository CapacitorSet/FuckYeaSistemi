

public class Predator extends Individual {

	int thirst;
	int hunger;

	Predator(Simulazione simulazione, float x, float y) {
		super(simulazione, x, y);
	}

	@Override
	void loadData() {
		this.r = 1;
		this.g = 0;
		this.b = 0;
		this.maxSpeed = 50;
		this.thirst = 10000;
		this.hunger = 10000;
		factor.put("WaterSource", thirst);
		factor.put("Prey", hunger);
	}

	@Override
	public void customTick() {
		thirst += 1000; // Increase the "thirst" (attraction to water sources) by 1k
		hunger += 1000;

		/* If the prey is no longer thirsty, its attraction to water sources is zero (no effect whatsoever),
		 * not negative (actively escaping from water sources).
		 */
		factor.put("WaterSource", (thirst > 0 ? thirst : 0));
		factor.put("Prey", (hunger > 0 ? hunger : 0));
	}

	public void interactWith(Item i) {
		if (i instanceof WaterSource && distanceTo(i) <= 10) {
			// If close to a water source, drink!
			thirst = -10000;
		}
		if (i instanceof Prey && distanceTo(i) <= 10) {
			// If close to a prey, eat it
			simulazione.kill(i);
			hunger = -10000;
		}
	}
}

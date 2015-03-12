/* Prey
 * Green, has thirst, escapes from Predators
 */

public class Prey extends Individual {

	int thirst;

	Prey(Simulazione simulazione, float x, float y) {
		super(simulazione, x, y);
	}

	@Override
	void loadData() {
		this.r = 0;
		this.g = 1;
		this.b = 0;
		this.maxSpeed = 50;
		this.thirst = 0;
		factor.put("WaterSource", thirst);
		factor.put("Predator", -100);
	}

	@Override
	public void customTick() {
		thirst += 2; // Increase the "thirst" (attraction to water sources)
		/* If the prey is no longer thirsty, its attraction to water sources is zero (no effect whatsoever),
		 * not negative (actively escaping from water sources).
		 */
		factor.put("WaterSource", (thirst > 0 ? thirst : 0));
	}

	public void interactWith(Item i) {
		if (i instanceof WaterSource && distanceTo(i) <= 10) {
			// If close to a water source, drink!
			thirst = -100;
		}
	}
}

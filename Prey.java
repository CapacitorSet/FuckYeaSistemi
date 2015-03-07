
import java.awt.Color;

public class Prey extends Individual {

	Prey(FuckYeaSistemi simulazione, float x, float y) {
		super(simulazione, x, y);
	}

	@Override
	void loadData() {
		this.color = Color.GREEN;
		this.maxSpeed = 50;
	}

	@Override
	public void customTick() {
		factor.put("WaterSource", factor.get("WaterSource") + 1000); // Increase the "thirst" (attraction to water sources) by 1k
	}

	public void birthTick() {
		if (random.nextInt(100) == 0) {
			simulazione.scheduleBirth(
					new Prey(simulazione, this.x + random.nextInt(20), this.y + random.nextInt(20))
			);
		}
		/* On each tick, there's a 1/100 chance of a new Prey being spawned
		 * (actually, being scheduled for birth) within 20 pixels of the
		 * present one
		 */
	}
}

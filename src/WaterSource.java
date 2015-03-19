
import java.util.List;
import java.util.Random;

public class WaterSource extends Item {

    public int amount;
	Random random;
    
    public WaterSource(Simulazione simulazione, Point position) {
        this.simulazione = simulazione;
        this.amount = 20;
        this.r = this.g = 0;
		this.b = 1;
        this.x = position.x;
        this.y = position.y;
		this.random = new Random();
    }
    
    public WaterSource(Simulazione simulazione, int x, int y) {
        this.simulazione = simulazione;
        this.r = this.g = 0;
		this.b = 1;
        this.x = x;
        this.y = y;
		this.random = new Random();
    }

    @Override
    public void velocityTick(List<Item> sistema, float dt) {
        if (random.nextInt(100) == 0) this.amount++; // May increase water level with chance 1/100
        if (this.amount > 20) this.amount = 20;
    }
    
    @Override
    public void positionTick(float dt) {
        return;
    }

    public void beConsumed() {
        this.amount--;
    }
}
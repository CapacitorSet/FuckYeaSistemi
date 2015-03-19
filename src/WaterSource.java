
import java.util.List;

public class WaterSource extends Item {

    public int amount;
    
    public WaterSource(Simulazione simulazione, Point position) {
        this.simulazione = simulazione;
        this.amount = 20;
        this.r = this.g = 0;
		this.b = 1;
        this.x = position.x;
        this.y = position.y;
    }
    
    public WaterSource(Simulazione simulazione, int x, int y) {
        this.simulazione = simulazione;
        this.r = this.g = 0;
		this.b = 1;
        this.x = x;
        this.y = y;
    }

    @Override
    public void velocityTick(List<Item> sistema, float dt) {
        this.amount++;
        if (this.amount > 20) this.amount = 20;
        return;
    }
    
    @Override
    public void positionTick(float dt) {
        return;
    }

    public void beConsumed() {
        this.amount--;
    }
}
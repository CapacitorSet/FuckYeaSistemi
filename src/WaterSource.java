
import java.awt.Color;
import java.util.List;

public class WaterSource extends Item {
    
    public WaterSource(FuckYeaSistemi simulazione, Point position) {
        this.simulazione = simulazione;
        this.color = Color.BLUE;
        this.x = position.x;
        this.y = position.y;
    }
    
    public WaterSource(FuckYeaSistemi simulazione, int x, int y) {
        this.simulazione = simulazione;
        this.color = Color.BLUE;
        this.x = x;
        this.y = y;
    }

    @Override
    public void velocityTick(List<Item> sistema, float dt) {
        return;
    }
    
    @Override
    public void positionTick(float dt) {
        return;
    }
}
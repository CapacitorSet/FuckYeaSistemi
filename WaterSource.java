
import java.awt.Color;

public class WaterSource extends Item {
    
    public WaterSource(Point position) {
        this.color = Color.BLUE;
        this.x = position.x;
        this.y = position.y;
    }
    
    public WaterSource(int x, int y) {
        this.color = Color.BLUE;
        this.x = x;
        this.y = y;
    }

    @Override
    public void velocityTick(Item[] sistema, float dt) {
        return;
    }
    
    @Override
    public void positionTick(float dt) {
        return;
    }
}
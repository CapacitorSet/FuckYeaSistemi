
import java.awt.Color;

public class WaterSource extends Item {
    public WaterSource(Point position) {
        this.x = position.x;
        this.y = position.y;
    }
    
    public WaterSource(int x, int y) {
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

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
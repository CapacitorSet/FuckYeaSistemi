
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
    public void tick(Item[] sistema, float dt) {
        // Do nothing, water sources don't "evolve"
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
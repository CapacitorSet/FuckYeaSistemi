
import java.awt.Color;

public class WaterSource extends Item {
    public Point position;
    
    public WaterSource(Point position) {
        this.position = position;
    }
    
    public WaterSource(int x, int y, int z) {
        this.position = new Point(x, y, z);
    }

    @Override
    public void tick(Item[] sistema, float dt) {
        // Do nothing, water sources don't "evolve"
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
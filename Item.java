
import java.awt.Color;

abstract public class Item extends Point {
    abstract public void velocityTick(Item[] sistema, float dt);
    abstract public void positionTick(float dt);
    public Color getColor() {
        return Color.BLACK;
    }
}

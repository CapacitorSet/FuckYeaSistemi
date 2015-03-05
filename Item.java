
import java.awt.Color;
import java.util.List;

abstract public class Item extends Point {
    abstract public void velocityTick(List<Item> sistema, float dt);
    abstract public void positionTick(float dt);
    public Color color;
}

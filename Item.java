
import java.awt.Color;

abstract public class Item extends Point {
    abstract public void tick(Item[] sistema, float dt);
    abstract public Point getPosition();
    public Color getColor() {
        return Color.BLACK;
    }
}

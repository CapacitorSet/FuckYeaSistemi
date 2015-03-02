
import java.awt.Color;

abstract public class Item {
    abstract public void tick(Item[] sistema, float dt);
    abstract public Point getPosition();
    public Color getColor() {
        return Color.BLACK;
    }
}

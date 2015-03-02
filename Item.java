
import java.awt.Color;

public interface Item {
    public void tick(Item[] sistema, float dt);
    public Point getPosition();
    public Color getColor();
}

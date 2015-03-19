
import java.util.List;

abstract public class Item extends Point {
    public Simulazione simulazione;
    abstract public void velocityTick(List<Item> sistema, float dt);
    abstract public void positionTick(float dt);
    abstract public void beConsumed();
    public float r, g, b;
}

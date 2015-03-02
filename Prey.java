
import java.awt.Color;

public class Prey extends GravityItem {

    Prey(int x, int y) {
        this.color = Color.GREEN;
        this.x = x;
        this.y = y;
        this.WaterSourceFactor = 10000;
    }
    
    @Override
    public void velocityTick(Item[] sistema, float dt) {
        updateVelocity(sistema, dt);
    }

    @Override
    public void positionTick(float dt) {
        updatePosition(dt);
    }
    
}

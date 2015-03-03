
import java.awt.Color;

public class Prey extends GravityItem {
    
    public int WaterSourceFactor;

    Prey(int x, int y) {
        this.color = Color.GREEN;
        this.x = x;
        this.y = y;
        this.WaterSourceFactor = 10000;
        this.maxSpeed = 50;
    }
    
    public Vector totalAcceleration(Item[] system) {
        Vector result = new Vector();
        for (Item a : system) {
            if (a != this) {
                if (a instanceof WaterSource) {
                    if (distanceTo(a)<10) {
                        this.WaterSourceFactor = 0; // If in the vicinity of a WaterSource, drink and don't get any closer
                    } else {
                        result.add(accelerationTo(a, WaterSourceFactor));
                    }
                } else {
                    // Do nothing, object not identified
                }
            }
        }
        return result;
    }
    
    @Override
    public void velocityTick(Item[] sistema, float dt) {
        Vector acceleration = totalAcceleration(sistema);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;
        float speed = (float) Math.sqrt(Math.pow(this.xVelocity, 2) + Math.pow(this.yVelocity, 2));
        if (speed > this.maxSpeed) {
            this.xVelocity = (this.xVelocity / speed) * this.maxSpeed;
            this.yVelocity = (this.yVelocity / speed) * this.maxSpeed;
        }
        // Todo: limit max self velocity
        this.WaterSourceFactor += 1000;
    }

    @Override
    public void positionTick(float dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }
    
}


import java.awt.Color;

public class Prey extends GravityItem {
    
    public int WaterSourceFactor; // Basically thirst

    Prey(int x, int y) { // Spawn a green point at given coordinates. Default thirst is 10k
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
                        this.WaterSourceFactor = 0; // If in the vicinity of a WaterSource, "drink" and don't get any closer
                    } else {
                        result.add(accelerationTo(a, WaterSourceFactor)); // Else, "feel attracted" to the WaterSource, in an amount proportional to thirst
                    }
                } else {
                    // Do nothing, object not identified. Feel neither attracted nor pushed away
                }
            }
        }
        return result;
    }
    
    @Override
    public void velocityTick(Item[] sistema, float dt) {
        // Do the obvious stuff: on each instant, the velocity increases by dt*acceleration
        Vector acceleration = totalAcceleration(sistema);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;
        
        limitSpeed();

        this.WaterSourceFactor += 1000; // Be more thirsty as time passes
    }

    @Override
    public void positionTick(float dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }
    
}


import java.awt.Color;
import java.util.List;
import java.util.Random;

public class Prey extends GravityItem {
    
    public int WaterSourceFactor; // Basically thirst
    Random random;

    Prey(FuckYeaSistemi simulazione, float x, float y) { // Spawn a green point at given coordinates. Default thirst is 10k
        random = new Random();
        this.simulazione = simulazione;
        this.color = Color.GREEN;
        this.x = x;
        this.y = y;
        this.WaterSourceFactor = 10000;
        this.maxSpeed = 50;
    }
    
    public Vector totalAcceleration(List<Item> system) {
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
    public void velocityTick(List<Item> sistema, float dt) {
        // Do the obvious stuff: on each instant, the velocity increases by dt*acceleration
        Vector acceleration = totalAcceleration(sistema);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;
        
        limitSpeed();

        this.thirstTick();
        this.birthTick();
    }

    public void thirstTick() {
        this.WaterSourceFactor += 1000; // Be more thirsty as time passes
    }
    
    public void birthTick() {
        if (random.nextInt(100)==0) {
            simulazione.scheduleBirth(
                new Prey(simulazione, this.x+random.nextInt(20), this.y+random.nextInt(20))
            );
        }
        /* On each tick, there's a 1/100 chance of a new Prey being spawned
         * (actually, being scheduled for birth) within 20 pixels of the
         * present one
         */
    }
    
    @Override
    public void positionTick(float dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }
    
}

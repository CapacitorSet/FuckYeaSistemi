
public abstract class GravityItem extends Item {

    public float WaterSourceFactor, PreyFactor;
    public float xVelocity, yVelocity, zVelocity;
    public static final int G = 1;

    public double distanceTo(Point a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2)
                + Math.pow(a.y - this.y, 2));
    }

    public Vector accelerationTo(Item a) {
        Vector result = new Vector();
        double thing = G / Math.pow(distanceTo(a), 3);
        if (a instanceof WaterSource)
            thing *= WaterSourceFactor;
        else if (a instanceof Prey)
            thing *= PreyFactor;
        else
            thing = 0;
        result.dX = (a.x - this.x) * thing;
        result.dY = (a.y - this.y) * thing;
        return result;
    }

    public Vector totalAcceleration(Item[] system) {
        Vector result = new Vector();
        for (Item a : system) {
            if (a != this) {
                result.add(accelerationTo(a));
            }
        }
        return result;
    }

    public void updateVelocity(Item[] system, float dt) {
        Vector acceleration = totalAcceleration(system);
        System.out.println(acceleration.dX);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;
    }

    public void updatePosition(float dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }
}

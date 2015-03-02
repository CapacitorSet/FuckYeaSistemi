
public abstract class GravityItem extends Item {

    public int mass;
    public float xVelocity, yVelocity, zVelocity;
    public static final int G = 1;

    public double distanceTo(GravityItem a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2)
                + Math.pow(a.y - this.y, 2));
    }

    public Vector accelerationTo(GravityItem a) {
        Vector result = new Vector();
        double thing = G * a.mass / Math.pow(distanceTo(a), 3);
        result.dX = (a.x - this.x) * thing;
        result.dY = (a.y - this.y) * thing;
        return result;
    }

    public Vector totalAcceleration(GravityItem[] system) {
        Vector result = new Vector();
        for (GravityItem a : system) {
            if (a != this) {
                result.add(accelerationTo(a));
            }
        }
        return result;
    }

    public void updateVelocity(GravityItem[] system, float dt) {
        Vector acceleration = totalAcceleration(system);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;
    }

    public void updatePosition(float dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }
}

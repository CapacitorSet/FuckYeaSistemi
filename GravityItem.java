
public abstract class GravityItem extends Item {
    
    // Provide useful functions for all moving items, i.e. those subject to "gravity"

    public float maxSpeed;
    public float xVelocity, yVelocity, zVelocity;
    public static final int G = 1;

    // Note: to be moved to the Terrain class to account for slopes and stuff
    public final double distanceTo(Point a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2)
                + Math.pow(a.y - this.y, 2));
    }

    // Provide the current acceleration towards an object, amplified by a factor "factor"
    /* F = m * a
     * a = F / m
     * a = (G * m1 * m2 / d^2) / m, where d is the distance
     *
     * Assume that m1*m2 = 1
     * a = (G / d^2) / m provides the total acceleration.
     * (deltaX/d) * a is the X component of the acceleration,
     * (deltaY/d) * a is the Y component of the acceleration.
     * 
     * Below, "thing" = a/d (so that we don't need to compute it twice)
     */
    public final Vector accelerationTo(Item a, float factor) {
        Vector result = new Vector();
        double thing = G / Math.pow(distanceTo(a), 3) * factor;
        result.dX = (a.x - this.x) * thing;
        result.dY = (a.y - this.y) * thing;
        return result;
    }
    
    public final Vector accelerationTo(Item a) {
        return accelerationTo(a, 1);
    }
    
    // Limit the speed: if the computed speed is too large, "resize" it without distorting it
    public final void limitSpeed(float maxSpeed) {
        float speed = (float) Math.sqrt(Math.pow(this.xVelocity, 2) + Math.pow(this.yVelocity, 2));
        if (speed > maxSpeed) {
            this.xVelocity = (this.xVelocity / speed) * this.maxSpeed;
            this.yVelocity = (this.yVelocity / speed) * this.maxSpeed;
        }
    }
    
    public final void limitSpeed() {
        limitSpeed(this.maxSpeed);
    }
}

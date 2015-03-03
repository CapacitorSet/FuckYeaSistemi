
public abstract class GravityItem extends Item {

    public float maxSpeed;
    public float xVelocity, yVelocity, zVelocity;
    public static final int G = 1;

    public double distanceTo(Point a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2)
                + Math.pow(a.y - this.y, 2));
    }

    public Vector accelerationTo(Item a, float weight) {
        Vector result = new Vector();
        double thing = G / Math.pow(distanceTo(a), 3) * weight;
        result.dX = (a.x - this.x) * thing;
        result.dY = (a.y - this.y) * thing;
        return result;
    }
    
    public Vector accelerationTo(Item a) {
        return accelerationTo(a, 1);
    }
}


public class Vector {

    public double dX, dY;

    public Vector() {
    }

    public Vector(double dX, double dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public Vector(double x1, double y1, double x2, double y2) {
        dX = Math.abs(x1 - x2);
        dY = Math.abs(y1 - y2);
    }

    public double length() {
        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
    }

    public void add(Vector v) {
        if (Double.isNaN(v.dX)) {
            v.dX = 0;
        }
        if (Double.isNaN(v.dY)) {
            v.dY = 0;
        }
        
        this.dX += v.dX;
        this.dY += v.dY;
    }
}

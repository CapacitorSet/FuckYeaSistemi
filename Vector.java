
public class Vector {

    public double dX, dY, dZ;

    public Vector() {
    }

    public Vector(double dX, double dY, double dZ) {
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    public Vector(double x1, double y1, double z1, double x2, double y2, double z2) {
        dX = Math.abs(x1 - x2);
        dY = Math.abs(y1 - y2);
        dZ = Math.abs(z1 - z2);
    }

    public double length() {
        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2) + Math.pow(dZ, 2));
    }

    public void add(Vector v) {
        if (Double.isNaN(v.dX)) {
            throw new ArithmeticException("Vector dX is NaN!");
        }
        if (Double.isNaN(v.dY)) {
            throw new ArithmeticException("Vector dY is NaN!");
        }
        if (Double.isNaN(v.dZ)) {
            throw new ArithmeticException("Vector dZ is NaN!");
        }
        this.dX += v.dX;
        this.dY += v.dY;
        this.dZ += v.dZ;
    }
}

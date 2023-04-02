public class Vec {
    private int x = 0, y = 0, z = 0;
    private double length = -1;

    public Vec() {
    }

    public Vec(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private void countLength(){
        length = Math.sqrt(x*x + y*y + z*z);
    }

    public static int scaProd(Vec v1, Vec v2){
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vec vecProd(Vec v1, Vec v2){
        Vec vec = new Vec();
        vec.x = v1.y * v2.z - v1.z * v2.y;
        vec.y = v1.z * v2.x - v1.x * v2.z;
        vec.z = v1.x * v2.y - v1.y * v2.x;
        return vec;
    }

    public static double angle(Vec v1, Vec v2){
        return Math.toDegrees(Math.acos(scaProd(v1, v2) / (v1.getLength() * v2.getLength())));
    }

    public double getLength() {
        if(length == -1) countLength();
        return length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

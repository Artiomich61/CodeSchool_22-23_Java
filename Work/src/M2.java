import java.util.Scanner;

public class M2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vec v1 = new Vec(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        Vec v2 = new Vec(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

        System.out.println("v1l - " + v1.getLength());
        System.out.println("v2l - " + v2.getLength());
        System.out.println(Vec.scaProd(v1, v2));
        Vec v3 = Vec.vecProd(v1, v2);
        System.out.println(v3.getX() + ", " + v3.getY() + ", " + v3.getZ());
        System.out.println("v3l - " + v3.getLength());
        System.out.println(Vec.angle(v1, v2));
    }
}

import java.util.Scanner;

public class KR2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        if(Integer.toBinaryString(n).charAt(Integer.toBinaryString(n).length() - 1) == '1'){
            n = n >> 4;
        }
        else {
            n &= 15;
        }
        System.out.println(n);
    }
}

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class _4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Object[] arr = new Object[scanner.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        if(arr.length > 1) {
            arr = cycleMove(arr);
        }
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i] + " ";
        }
        fout(str);
    }
    static Object[] cycleMove(Object[] arr){
        Object temp = arr[0];
        for (int i = arr.length; i > 1; i--) {
            arr[i % arr.length] = arr[(i - 1) % arr.length];
        }
        arr[1] = temp;
        return arr;
    }
    static Object[] cycleMove(Object[] arr, int stages){
        
        return arr;
    }

    static void fout(String str){
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream("output.txt");
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}

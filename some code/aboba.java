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
    
    static String fin(String fileName){
        String str = "";
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        BufferedReader bufferedReader;
        try {
            fileInputStream = new FileInputStream(fileName);
            bufferedInputStream = new BufferedInputStream(fileInputStream, 20);
            bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            str = bufferedReader.readLine();
            bufferedReader.close();
            bufferedInputStream.close();
            fileInputStream.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return str;
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

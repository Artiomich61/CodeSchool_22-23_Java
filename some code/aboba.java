import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class _4 {
    
    static line = 1; //для fin2
    
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
        Object temp;
        for (int i = 0; i < stages; i++) {
            temp = arr[0];
            for (int j = arr.length; j > 1; j--) {
                arr[j % arr.length] = arr[(j - 1) % arr.length];
            }
            arr[1] = temp;
        }

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
    
    static String fin2(String fileName){ //fin в несколько строчек
        String str = "";
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        BufferedReader bufferedReader;
        try {
            fileInputStream = new FileInputStream(fileName);
            bufferedInputStream = new BufferedInputStream(fileInputStream, 20);
            bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            for (int i = 1; i < line; i++) {
                bufferedReader.readLine();
            }
            str = bufferedReader.readLine();
            bufferedReader.close();
            bufferedInputStream.close();
            fileInputStream.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        line++;
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

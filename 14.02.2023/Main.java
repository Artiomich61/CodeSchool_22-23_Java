import java.io.*;

public class Main {
    public static void main(String[] args) {
        String input = fin("input.txt");
        String output = "";
        int a = Integer.parseInt(input.split(" ")[0]);
        int b = Integer.parseInt(input.split(" ")[1]);
        if(a % 2 != 0) a++;
        for (int i = a; i <= b; i += 2) {
            output += i + " ";
        }
        System.out.println(output);
        fout(output);
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

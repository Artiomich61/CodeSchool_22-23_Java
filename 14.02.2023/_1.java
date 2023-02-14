import java.io.*;

public class _1 {
    static byte line = 1;
    public static void main(String[] args) {
        String output = "";
        int a = Integer.parseInt(fin("input.txt")), b = Integer.parseInt(fin("input.txt")),
                c = Integer.parseInt(fin("input.txt")), d = Integer.parseInt(fin("input.txt"));
        while(a % d != c) a++;
        for (int i = a; i <= b; i += d) {
            output += i + " ";
        }
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

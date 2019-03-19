package main.java.e001_composite;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Client {
    public static void main(String[] args) {
        int c;

        StringBuffer stringBuffer = new StringBuffer("Jason Freddie aaaBBBcccDDDeFF");
        byte[] bytes = stringBuffer.toString().getBytes();

        try(InputStream in = new LowerCaseInputStream(new ByteArrayInputStream(bytes))){
            while((c = in.read()) >= 0){
                System.out.print((char)c);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

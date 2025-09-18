package TCP.ByteStream;

import java.io.*;
import java.net.*;

public class FD2Y5J3A {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "FD2Y5J3A";
        String host = "203.162.10.109";
        int port = 2206;

        try(
            Socket socket = new Socket(host, port);
            OutputStream writer = socket.getOutputStream();
            InputStream reader = socket.getInputStream();
        ){
            String toSend = studentCode + ";" + qCode;
            writer.write(toSend.getBytes());
            writer.flush();

            byte[] buffer = new byte[1024];
            int readBytes = reader.read(buffer);
            if (readBytes!=-1){
                String received = new String(buffer, 0, readBytes);
                String[] numbers = received.split(",");
                int[] numberArray = new int[numbers.length];
                for (int i=0; i<numbers.length; i++){
                    numberArray[i] = Integer.parseInt(numbers[i].trim());
                }
                int sum=0; 
                for (int i=0; i<numberArray.length; i++){
                    if (isPrime(numberArray[i])){
                        sum+=numberArray[i];
                    }
                }
                writer.write((sum+"").getBytes());
                writer.flush();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static boolean isPrime(int n){
        if (n<2){
            return false;
        }
        else{
            for (int i=2; i<=Math.sqrt(n); i++){
                if (n%i==0){
                    return false; 
                }
            }
            return true;
        }
    }
}

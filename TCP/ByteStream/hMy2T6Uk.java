package TCP.ByteStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class hMy2T6Uk {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "hMy2T6Uk";
        String host = "203.162.10.109";
        int port = 2206;

        try (
            Socket socket = new Socket(host, port);
            InputStream reader = socket.getInputStream();
            OutputStream writer = socket.getOutputStream();
        ){
            String toSend = studentCode + ";" + qCode;
            writer.write(toSend.getBytes());
            writer.flush();

            byte[] buffer = new byte[1024];
            int receiveBuffer = reader.read(buffer);
            int sumAllArray = 0;
            if (receiveBuffer!=-1){
                String received = new String(buffer,0,receiveBuffer);
                String[] words = received.split(",");
                int[] numbers = new int[words.length];
                for (int i=0; i<words.length; i++){
                    int current = Integer.parseInt(words[i]);
                    numbers[i] = current;
                    sumAllArray+=current;
                }
                double average = (double) sumAllArray / words.length;
                double target = 2*average;

                int num1 = 0, num2 = 0;
                double minDiff = Double.MAX_VALUE;
                for (int i = 0; i < numbers.length; i++) {
                    for (int j = i + 1; j < numbers.length; j++) {
                        if (numbers[i] < numbers[j]) { // Ensure num1 < num2
                            int currentSum = numbers[i] + numbers[j];
                            double diff = Math.abs(currentSum - target);
                            if (diff < minDiff) {
                                minDiff = diff;
                                num1 = numbers[i];
                                num2 = numbers[j];
                            }
                        }
                    }
                }

                if (minDiff!=Double.MAX_VALUE){
                    writer.write((num1+","+num2).getBytes());
                    writer.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

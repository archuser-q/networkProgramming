package TCP.ByteStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Ck7JrTDW {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "Ck7JrTDW";
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
            int bytesRead = reader.read(buffer);

            int sum=0;

            if (bytesRead!=-1){
                String received = new String(buffer, 0, bytesRead);
                String[] processed = received.split("\\|");
                for (String word:processed){
                    sum+=Integer.parseInt(word);
                }
                writer.write((sum+"").getBytes());
                writer.flush();
            }
        } catch (Exception e) {
        }
    }
}

import java.io.*;
import java.net.*;

public class SfrAzCih{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "SfrAzCih";
        String host = "203.162.10.109";
        int port = 2207;

        try (
            Socket socket = new Socket(host, port);
        ){
            socket.setSoTimeout(5000);
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            String toSend = studentCode+";"+qCode;
            writer.writeUTF(toSend);
            writer.flush();

            int received = reader.readInt();
            if(String.valueOf(received).length()>0){
                String binary = Integer.toBinaryString(received);
                String hex = Integer.toHexString(received).toUpperCase();

                String response = binary+";"+hex;
                writer.writeUTF(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
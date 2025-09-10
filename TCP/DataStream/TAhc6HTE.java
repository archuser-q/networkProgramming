import java.io.*;
import java.net.*;

public class TAhc6HTE{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "TAhc6HTE";
        String host = "203.162.10.109";
        int port = 2207;

        try (
            Socket socket = new Socket(host, port);
        ){
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            
            String toSend = studentCode + ";" + qCode;
            writer.writeUTF(toSend);
            writer.flush();

            int received = reader.readInt();
            StringBuilder sb = new StringBuilder();
            String octal = Integer.toOctalString(received);
            sb.append(octal);
            sb.append(";");
            String hex = Integer.toHexString(received).toUpperCase();
            sb.append(hex);
            writer.writeUTF(sb.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
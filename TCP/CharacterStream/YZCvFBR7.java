package TCP.CharacterStream;
import java.io.*;
import java.net.*;

public class YZCvFBR7 {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "YZCvFBR7";
        String host = "203.162.10.109";
        int port = 2208;

        try (
            Socket socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ){
            String toSend = studentCode+";"+qCode;
            writer.write(toSend);
            writer.newLine();
            writer.flush();

            String received = reader.readLine();
            if (received.length()>0){
                String[] received_pr = received.split(", ");
                StringBuilder sb = new StringBuilder();
                for (String i:received_pr){
                    if (i.endsWith(".edu")){
                        sb.append(i);
                        sb.append(", ");
                    }
                }
                String response = sb.deleteCharAt(sb.length()-2).toString().trim();
                writer.write(response);
                writer.newLine();
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

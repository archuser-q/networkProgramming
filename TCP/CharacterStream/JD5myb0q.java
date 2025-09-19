package TCP.CharacterStream;

import java.io.*;
import java.net.*;

public class JD5myb0q{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "JD5myb0q";
        String host = "203.162.10.109";
        int port = 2208;

        try (
            Socket socket = new Socket(host, port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            String toSend = studentCode+";"+qCode;
            writer.write(toSend);
            writer.newLine();
            writer.flush();

            String[] words = reader.readLine().split(", ");
            StringBuilder response = new StringBuilder();
            for (String word:words){
                if (word.endsWith(".edu")){
                    response.append(word);
                    response.append(", ");
                }
            }
            if (response.length()>0){
                response.setLength(response.length()-2);
            }
            writer.write(response.toString());
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
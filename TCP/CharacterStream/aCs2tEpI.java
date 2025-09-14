package TCP.CharacterStream;

import java.io.*;
import java.net.*;

public class aCs2tEpI{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "aCs2tEpI";
        String host = "203.162.10.109";
        int port = 2208;

        try (
            Socket socket = new Socket(host, port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            String toSend = studentCode + ";" + qCode;
            writer.write(toSend);
            writer.newLine();
            writer.flush();

            String[] words = reader.readLine().split(" ");
            StringBuilder response = new StringBuilder();
            for (String word: words){
                StringBuilder sb = new StringBuilder(word);
                response.append(RLE(sb.reverse()));
            }
            writer.write(response.toString());
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String RLE(StringBuilder s){
        int count=1; 
        char temp = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<s.length(); i++){
            char current = s.charAt(i);
            if (current == temp){
                count++;
            }
            else{
                sb.append(temp);
                if (count>1){
                    sb.append(count);
                }
                temp = current; 
                count = 1;            }
        }
        sb.append(temp);
        if (count>1){
            sb.append(count);
        }
        sb.append(" ");
        return sb.toString();
    }
}
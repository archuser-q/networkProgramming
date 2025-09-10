package TCP.CharacterStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
public class aCs2tEpI {
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
            //a)
            String toSend = studentCode + ";" + qCode;
            writer.write(toSend);
            writer.newLine();
            writer.flush();
            //b)
            String received = reader.readLine();
            //c)
            String[] words = received.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String word:words){
                if(sb.length()>0){
                    sb.append(" ");
                }
                String reversed = new StringBuilder(word).reverse().toString();
                sb.append(applyRLE(reversed));
            }
            writer.write(sb.toString());
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String applyRLE(String reversed){
        if (reversed.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        char prev = reversed.charAt(0);
        int count=1; 

        for (int i=1; i<reversed.length(); i++){
            char current = reversed.charAt(i);
            if (current == count){
                count++;
            }
            else{
                sb.append(prev);
                if (count>1){
                    sb.append(count);
                }
                prev = current;
                count=1;
            }
        }
        sb.append(prev);
        if(count>1){
            sb.append(count);
        }
        return sb.toString();
    }
}

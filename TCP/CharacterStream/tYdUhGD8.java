package TCP.CharacterStream;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class tYdUhGD8{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "tYdUhGD8";
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

            String received = reader.readLine();

            String[] words = received.split(" ");
            Arrays.sort(words, (s1,s2)-> s1.length() - s2.length());

            String formatted = String.join(", ",words);
            writer.write(formatted);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
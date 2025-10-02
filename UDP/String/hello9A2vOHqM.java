package UDP.String;

import java.io.*;
import java.net.*;
import java.util.*;

public class hello9A2vOHqM {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "9A2vOHqM";
        String host = "203.162.10.109";
        int port = 2208;
        
        try (
            DatagramSocket socket = new DatagramSocket();
        ){
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            String toSend = ";"+studentCode+";"+qCode;
            byte[] messageBuffer = toSend.getBytes();
            DatagramPacket messagePacket = new DatagramPacket(messageBuffer, messageBuffer.length, address, port);
            socket.send(messagePacket);

            byte[] receivedBuffer = new byte[4096];
            DatagramPacket preprocessedBuffer = new DatagramPacket(receivedBuffer, receivedBuffer.length, address, port);
            socket.receive(preprocessedBuffer);
            String processedBuffer = new String(preprocessedBuffer.getData(), 0, preprocessedBuffer.getLength());
            String[] rawBuffer = processedBuffer.split(";");
            String requestId = rawBuffer[0];
            String[] characterSequence = rawBuffer[1].split("\\s+");
            
            List<String> wordList = Arrays.asList(characterSequence);
            Collections.sort(wordList, (a, b) -> b.compareToIgnoreCase(a));
            StringBuilder sb = new StringBuilder();
            for (String i:wordList){
                sb.append(i);
                sb.append(",");
            }
            String response = requestId+";"+sb.deleteCharAt(sb.length()-1).toString();
            byte[] responseBuffer = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(responsePacket);
        } catch(SocketTimeoutException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

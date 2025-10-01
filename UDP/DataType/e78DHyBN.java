package UDP.DataType;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class e78DHyBN {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "e78DHyBN";
        String host = "203.162.10.109";
        int port = 2207;
        
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
            DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length, address, port);
            socket.receive(receivedPacket);
            String preprocessedPacket = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            String[] processedPacket = preprocessedPacket.split(";");
            String requestedID = processedPacket[0];
            BigInteger a = new BigInteger(processedPacket[1].trim());
            BigInteger b = new BigInteger(processedPacket[2].trim());
            BigInteger sum = a.add(b);
            BigInteger difference = a.subtract(b).abs();
            String response = requestedID+";"+sum+","+difference;
            byte[] responseBuffer = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(responsePacket);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

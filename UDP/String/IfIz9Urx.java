package UDP.String;

import java.net.*;

public class IfIz9Urx {
    public static String normalize(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(s.charAt(0)));
        sb.append(s.substring(1).toLowerCase());
        return sb.toString();
    }
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "IfIz9Urx";
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
            String requestedID = rawBuffer[0];
            String[] data = rawBuffer[1].trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String i:data){
                sb.append(normalize(i));
                sb.append(" ");
            }
            String response = requestedID+";"+sb.toString();
            byte[] responseBuffer = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(responsePacket);
        } catch(SocketTimeoutException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

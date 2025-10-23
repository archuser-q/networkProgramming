package UDP.String;
import java.net.*;
public class CNy5DoWx {
    public static String normalize(String i){
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(i.charAt(0)));
        sb.append(i.substring(1).toLowerCase());
        return sb.toString();
    }
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "CNy5DoWx";
        String host = "203.162.10.109";
        int port = 2208;

        try (
            DatagramSocket socket = new DatagramSocket();
        ){
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            String toSend = ";"+studentCode+";"+qCode;
            byte[] messagebuffer = toSend.getBytes();
            DatagramPacket messagepacket = new DatagramPacket(messagebuffer, messagebuffer.length, address, port);
            socket.send(messagepacket);

            byte[] receivedbuffer = new byte[4096];
            DatagramPacket receivedpacket = new DatagramPacket(receivedbuffer, receivedbuffer.length, address, port);
            socket.receive(receivedpacket);
            String pr_receivedBuffer = new String(receivedpacket.getData(),0,receivedpacket.getLength());
            String[] receivedBuffer = pr_receivedBuffer.split(";");
            String requestId = receivedBuffer[0];
            String[] data = receivedBuffer[1].trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String i:data){
                sb.append(normalize(i));
                sb.append(" ");
            }
            String response = requestId+";"+sb.toString().trim();
            byte[] responseBuffer = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(responsePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

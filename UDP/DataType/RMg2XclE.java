package UDP.DataType;

import java.net.*;

public class RMg2XclE {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "RMg2XclE";
        String host = "203.162.10.109";
        int port = 2207;

        try (
            DatagramSocket socket = new DatagramSocket();
        ){
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);
            String toSend = ";"+studentCode+";"+qCode;
            byte[] messagebuffer = toSend.getBytes();
            DatagramPacket messagePacket = new DatagramPacket(messagebuffer, messagebuffer.length, address, port);
            socket.send(messagePacket);

            byte[] buffer = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length, address, port);
            socket.receive(receivedPacket);
            String pr_received = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            String[] received = pr_received.split(";");
            String requestId = received[0];
            String[] data = received[1].split(",");

            StringBuilder sb = new StringBuilder();
            int n = data.length;
            for (int i=1; i<=n; i++){
                for (String j:data){
                    String[] splitted = j.split(":");
                    if(splitted.length>=2){
                        int consideration = Integer.parseInt(splitted[splitted.length-1]);
                        if (consideration==i){
                            sb.append(splitted[0]);
                            sb.append(",");
                            break;
                        }
                    }
                }
            }

            String response = requestId+";"+sb.deleteCharAt(sb.length()-1).toString();
            byte[] responseBuffer = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(responsePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

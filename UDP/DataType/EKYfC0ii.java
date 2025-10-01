package UDP.DataType;

import java.net.*;
import java.util.*;

public class EKYfC0ii {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "EKYfC0ii";
        String host = "203.162.10.109";
        int port = 2207;

        try (
            DatagramSocket socket = new DatagramSocket();
        ){
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            String toSend = ";" + studentCode + ";" + qCode;
            byte[] messageBuffer = toSend.getBytes();
            DatagramPacket messagePacket = new DatagramPacket(messageBuffer, messageBuffer.length, address, port);
            socket.send(messagePacket);

            byte[] receivedBuffer = new byte[4096];
            DatagramPacket preprocessedBuffer = new DatagramPacket(receivedBuffer, receivedBuffer.length, address, port);
            socket.receive(preprocessedBuffer);
            String processedBuffer = new String(preprocessedBuffer.getData(), 0, preprocessedBuffer.getLength()).trim();

            if (processedBuffer.length()<2){
                return;
            }
            String[] rawBuffer = processedBuffer.split(";");
            String requestedId = rawBuffer[0];
            String[] numbers = rawBuffer[1].split(",");
            Integer[] array = new Integer[numbers.length];

            for (int i=0; i<numbers.length; i++){
                array[i] = Integer.parseInt(numbers[i].trim());
            }

            Arrays.sort(array);
            int secondMin = array[1];
            int secondMax = array[array.length-2];

            String secondResult = requestedId+";"+secondMax+","+secondMin;
            byte[] resultBuffer = secondResult.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultBuffer, resultBuffer.length, address, port);
            socket.send(resultPacket);
        }catch(SocketTimeoutException e){
            e.printStackTrace();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

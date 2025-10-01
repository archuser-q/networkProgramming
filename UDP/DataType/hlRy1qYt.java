package UDP.DataType;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;

public class hlRy1qYt {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "hlRy1qYt";
        String host = "203.162.10.109";
        int port = 2207;

        try (
            DatagramSocket socket = new DatagramSocket();
        ){
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            //a) Gửi thông tin lên server
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendMessage = message.getBytes();
            DatagramPacket sentPackage = new DatagramPacket(sendMessage, sendMessage.length, address, port);
            socket.send(sentPackage);

            //b) Nhận chuỗi dữ liệu từ server 
            byte[] buffer = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivedPacket);
            String response = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
            System.out.println("Received: "+response);

            //c) Tìm phần tử min max 
            String[] parts = response.split(";");
            String requestID = parts[0];
            String[] numbers = parts[1].split(",");

            Integer[] array = new Integer[numbers.length];
            for (int i=0; i<array.length; i++){
                array[i] = Integer.parseInt(numbers[i].trim());
            } 

            Arrays.sort(array);

            int min = array[0];
            int max = array[array.length-1];

            String result = requestID + ";" + max + "," + min;
            byte[] resultData = result.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, address, port);
            socket.send(resultPacket);
        } catch(SocketTimeoutException e){
            e.printStackTrace();
        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package UDP.Object;

import UDP.Product;
import java.io.*;
import java.net.*;

public class nDJFaK2J {
    public static void main(String[] args) {
        String studentCode = "B22DCVT419";
        String qCode = "nDJFaK2J";
        String host = "203.162.10.109";
        int port = 2209;

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000); // timeout 5s
            InetAddress address = InetAddress.getByName(host);

            String toSend = ";" + studentCode + ";" + qCode;
            byte[] messageBuffer = toSend.getBytes();
            DatagramPacket messagePacket = new DatagramPacket(
                    messageBuffer, messageBuffer.length, address, port);
            socket.send(messagePacket);

            byte[] objectBuffer = new byte[4096];
            DatagramPacket objectPacket = new DatagramPacket(objectBuffer, objectBuffer.length);
            socket.receive(objectPacket);

            byte[] data = objectPacket.getData();
            int length = objectPacket.getLength();

            String requestId = new String(data, 0, 8, "UTF-8");

            ByteArrayInputStream bis = new ByteArrayInputStream(data, 8, length - 8);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Product product = (Product) ois.readObject();
            ois.close();

            System.out.println("Nhận Product: " + product);

            String[] words = product.getName().split(" ");
            if (words.length > 1) {
                String temp = words[0];
                words[0] = words[words.length - 1];
                words[words.length - 1] = temp;
                product.setName(String.join(" ", words));
            }

            String quantityStr = new StringBuilder(
                    String.valueOf(product.getQuantity())).reverse().toString();
            product.setQuantity(Integer.parseInt(quantityStr));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(product);
            oos.flush();
            byte[] objectData = bos.toByteArray();

            byte[] sendData = new byte[8 + objectData.length];
            System.arraycopy(requestId.getBytes(), 0, sendData, 0, 8);
            System.arraycopy(objectData, 0, sendData, 8, objectData.length);

            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length, address, port);
            socket.send(sendPacket);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

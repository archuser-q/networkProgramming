package UDP.Object;
import java.io.*;
import java.net.*;

import UDP.Student;

public class hmaakHEQ {
    public static String normalize(String s){
        String[] sSplit = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String i:sSplit){
            sb.append(Character.toUpperCase(i.charAt(0)));
            sb.append(i.substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    public static String createEmail(String s){
        String[] sSplit = s.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(sSplit[sSplit.length-1].toLowerCase());
        for (int i=0; i<sSplit.length-1; i++){
            sb.append(Character.toLowerCase(sSplit[i].charAt(0)));
        }
        return sb.toString() + "@ptit.edu.vn";
    }
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "hmaakHEQ";
        String host = "203.162.10.109";
        int port = 2209;
        
        try(DatagramSocket socket = new DatagramSocket()){
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);
            
            String toSend = ";"+studentCode+";"+qCode;
            byte[] messagebuffer = toSend.getBytes();
            DatagramPacket messagepacket = new DatagramPacket(messagebuffer, messagebuffer.length, address, port);
            socket.send(messagepacket);
            
            byte[] received = new byte[4096];
            DatagramPacket receivedpacket = new DatagramPacket(received, received.length);
            socket.receive(receivedpacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(receivedpacket.getData(),8,receivedpacket.getLength()-8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student student = (Student) ois.readObject();
            ois.close();
            
            String requestId = new String(receivedpacket.getData(), 0, 8).trim();
            
            String formattedName = normalize(student.getName());
            student.setName(formattedName);
            
            String email = createEmail(formattedName);
            student.setEmail(email);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes());
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();
            
            byte[] finalData = baos.toByteArray();
            DatagramPacket finalpacket = new DatagramPacket(finalData, finalData.length, address, port);
            socket.send(finalpacket);
            
            System.out.println("Socket close");
            socket.close();
        }catch(SocketTimeoutException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

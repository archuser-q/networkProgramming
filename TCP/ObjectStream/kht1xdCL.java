package TCP.ObjectStream;

import java.io.*;
import java.net.*;

import TCP.Product;

public class kht1xdCL {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "kht1xdCL";
        String host = "203.162.10.109";
        int port = 2209;

        try(
            Socket socket = new Socket(host, port);
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        ){
            writer.flush();
            String toSend = studentCode + ";" + qCode;
            writer.writeObject(toSend);
            System.out.print(writer);
            writer.flush();

            Product product = (Product) reader.readObject();
            int discount = 0;
            int intPrice = (int) product.getPrice();
            while (intPrice > 0) {
                discount += intPrice % 10;
                intPrice /= 10;
            }
            product.setDiscount(discount);
            writer.writeObject(product);
            writer.flush();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

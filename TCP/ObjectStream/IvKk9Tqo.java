package TCP.ObjectStream;

import TCP.Address;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IvKk9Tqo{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "IvKk9Tqo";
        String host = "203.162.10.109";
        int port = 2209;

        try (
            Socket socket = new Socket(host, port);
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        ){
            writer.flush();
            String toSend = studentCode+";"+qCode;
            writer.writeObject(toSend);
            writer.flush();

            Address getObject = (Address) reader.readObject();


            //Standardizing address line
            String[] preprocessed = getObject.getAddressLine().replaceAll("[^a-zA-Z0-9]"," ").split("\\s+");
            StringBuilder standardizeAddressLine = new StringBuilder();
            for (String element:preprocessed){
                standardizeAddressLine.append(Character.toUpperCase(element.charAt(0)));
                standardizeAddressLine.append(element.substring(1).toLowerCase());
                standardizeAddressLine.append(' ');
            }
            getObject.setAddress(standardizeAddressLine.toString());


            //Standardizing postal code
            String preprocessed_2 = getObject.getPosCode();
            StringBuilder standardizePostalCode = new StringBuilder();
            for (int i=0; i<preprocessed_2.length(); i++){
                char current = preprocessed_2.charAt(i);
                if (current=='-' || Character.isDigit(current)){
                    standardizePostalCode.append(current);
                }
            }
            getObject.setposCode(standardizePostalCode.toString());
            
            writer.writeObject(getObject);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

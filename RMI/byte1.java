package RMI;

import java.rmi.registry.*;

public class byte1 {
    public static void main(String[] args){
        try {
            String studentCode = "B22DCVT419";
            String qCode = "mJQrB8Oh";

            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
            ByteService service = (ByteService) registry.lookup("RMIByteService");

            byte[] data = service.requestData(studentCode, qCode);

            int shift = data.length;

            byte[] encoded = new byte[data.length];

            for (int i=0; i<data.length; i++){
                encoded[i] = (byte)(data[i]+shift);
            }
            service.submitData(studentCode, qCode, encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

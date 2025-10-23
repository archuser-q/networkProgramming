package RMI;

import java.rmi.registry.*;

public class Nt3xgRzb {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "Nt3xgRzb";
        String host = "203.162.10.109";
        int port = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            DataService service = (DataService) registry.lookup("RMIDataService");

            Integer received = (int) service.requestData(studentCode, qCode);
            int[] array = {1,2,5,10};
            
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            if (sum<received){
                for (int i=0; i<array.length; i++){
                    sum+=array[i];
                }
            }
        } catch (Exception e) {
        }
    }
}

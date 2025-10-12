package RMI; 

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class data {
    public static void main(String[] args) {
        try {
            String studentCode = "B22DCVT419";
            String qCode = "MosECsF5";
            
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
            DataService service = (DataService) registry.lookup("RMIDataService");
            
            Integer N = (Integer) service.requestData(studentCode, qCode);
            
            List<Integer> primes = new ArrayList<>();
            for (int i = 2; i <= N; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
            
            service.submitData(studentCode, qCode, primes);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
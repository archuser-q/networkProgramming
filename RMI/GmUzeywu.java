package RMI;

import java.rmi.registry.*;

public class GmUzeywu {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "GmUzeywu";
        String host = "203.162.10.109";
        int port = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

            String received = service.requestCharacter(studentCode, qCode);
            StringBuilder sb = new StringBuilder();
            String check = "";
            for (int i=0; i<received.length(); i++){
                char compare = received.charAt(i);
                if (check.indexOf(compare)!=-1) continue;
                
                int count = 0;
                for (int j=0; j<received.length(); j++){
                    if (received.charAt(j)==compare){
                        count++;
                    }
                }
                sb.append(compare).append(String.valueOf(count));
                check+=compare;
            }
            String response = sb.toString();
            service.submitCharacter(studentCode, qCode, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

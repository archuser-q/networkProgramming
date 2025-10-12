package RMI;

import java.rmi.registry.*;

public class character {
    public static void main(String[] args){
        try {
            String studentCode = "B22DCVT419";
            String qCode = "NbXUKvt8";
            
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

            String received = service.requestCharacter(studentCode, qCode);

            String[] process = received.split(";");

            String key = process[0];
            String input = process[1];

            String encoded = key + xor(input, key);

            service.submitCharacter(studentCode, qCode, encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String xor(String input, String key){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<input.length(); i++){
            char inputChar = input.charAt(i);
            char keyChar = key.charAt(i%key.length());
            sb.append((char)(inputChar ^ keyChar));
        }
        return sb.toString();
    }
}

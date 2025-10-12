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

            String[] processed = received.split(";");
            String key = processed[0];
            String unEncoded = processed[1];

            String encoded = xor(unEncoded, key);

            service.submitCharacter(studentCode, qCode, encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String xor(String s, String key){
        StringBuilder result = new StringBuilder();
        for (int i=0; i<s.length(); i++){
            char inputChar = s.charAt(i);
            char keyChar = key.charAt(i%key.length());
            result.append((char) (inputChar ^ keyChar));
        }
        return result.toString();
    }
}

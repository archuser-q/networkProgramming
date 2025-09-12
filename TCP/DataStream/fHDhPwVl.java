
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class fHDhPwVl{
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "fHDhPwVl";
        String host = "203.162.10.109";
        int port = 2207;

        try (
            Socket socket = new Socket(host, port);
        ){
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

            String toSend = studentCode + ";" + qCode;
            writer.writeUTF(toSend);
            writer.flush();

            String received = reader.readUTF();
            String[] process = received.trim().split(",");
            int[] array = new int [process.length];
            for (int i=0; i<process.length; i++){
                array[i] = Integer.parseInt(process[i].trim());
            }

            int changeCount = 0;
            int sum = 0;
            int prevDiff = array[1]-array[0];
            sum+=Math.abs(prevDiff);

            for (int i=2; i<array.length; i++){
                int currentDiff = array[i] - array[i-1];
                sum+=Math.abs(currentDiff);

                if ((prevDiff>0 && currentDiff<0) || (prevDiff<0 && currentDiff>0)){
                    changeCount++;
                }
                prevDiff = currentDiff;
            }
            writer.writeInt(changeCount);
            writer.writeInt(sum);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
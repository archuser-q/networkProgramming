
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class yeDohRjg {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "yeDohRjg";
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

            int a = reader.readInt();
            int b = reader.readInt();

            int sum = a+b;
            int product = a*b;

            writer.writeInt(sum);
            writer.writeInt(product);

            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

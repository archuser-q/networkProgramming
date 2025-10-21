package TCP.ByteStream;

import java.io.*;
import java.net.*;
import java.util.*;

public class dDA4Ki8n {
    public static void main(String[] args){
        String studentCode = "B22DCVT419";
        String qCode = "dDA4Ki8n";
        String host = "203.162.10.109";
        int port = 2206;

        try (
            Socket socket = new Socket(host, port);
            InputStream reader = socket.getInputStream();
            OutputStream writer = socket.getOutputStream();
        ){
            String toSend = studentCode + ";" + qCode;
            writer.write(toSend.getBytes());
            writer.flush();

            byte[] buffer = new byte[4096];
            int bytesRead = reader.read(buffer);

            if (bytesRead != -1) {
                String received = new String(buffer, 0, bytesRead).trim();
                String[] parts = received.split(",");
                int n = parts.length;
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i].trim());

                int[] dp = new int[n];
                int[] prev = new int[n];
                Arrays.fill(dp, 1);
                Arrays.fill(prev, -1);

                int maxLen = 1, lastIndex = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < i; j++) {
                        if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            prev[i] = j;
                        }
                    }
                    if (dp[i] > maxLen) {
                        maxLen = dp[i];
                        lastIndex = i;
                    }
                }

                List<Integer> seq = new ArrayList<>();
                for (int i = lastIndex; i != -1; i = prev[i]) seq.add(arr[i]);
                Collections.reverse(seq);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < seq.size(); i++) {
                    sb.append(seq.get(i));
                    if (i < seq.size() - 1) sb.append(",");
                }

                String result = sb + ";" + maxLen;
                writer.write(result.getBytes());
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

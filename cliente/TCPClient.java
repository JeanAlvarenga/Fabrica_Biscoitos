package cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class TCPClient {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("192.168.0.116", 9090); //localhost
        OutputStreamWriter write = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("User", "Jean" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        write.write(jsonObject.toString() + "\n");
        write.flush();
        
        socket.close();
    }
}


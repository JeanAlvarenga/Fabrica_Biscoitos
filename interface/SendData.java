import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendData {

    public void send(String ip, String data) throws IOException {
        Socket socket = new Socket(ip, 9001); //localhost
        PrintWriter write = new PrintWriter(socket.getOutputStream(), true);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("Dados", data);
        
        write.close();
        socket.close();
    }
}

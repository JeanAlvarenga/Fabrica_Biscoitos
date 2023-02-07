package cliente;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("192.168.0.116", 9090); //localhost
        PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("User", "Daniel");
        jsonObject.put("Password", "12345678");
        jsonObject.put("Tipo", "Recheado");
        jsonObject.put("Ing1", "1");
        jsonObject.put("Ing2", "2");
        jsonObject.put("Ing3", "3");
        
        write.println(jsonObject.toString()); //envia o json para o servidor

        String response = reader.readLine();
        JsonNode jsonNode = mapper.readTree(response);
        String user = jsonNode.get("status").asText();
        String password = jsonNode.get("message").asText();

        System.out.println("Status: " + user);
        System.out.println("Message: " + password);
        
        reader.close();
        write.close();
        socket.close();
    }
}


package cliente;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TCPClient {

    public TCPClient() {

    }

    public String createJSON(String ip, String us, String pass, String tip, String i1, String i2, String i3) throws Exception{
        Socket socket = new Socket(ip, 9090); //localhost
        PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("User", us);
        jsonObject.put("Password", pass);
        jsonObject.put("Tipo", tip);
        jsonObject.put("Ing1", i1);
        jsonObject.put("Ing2", i2);
        jsonObject.put("Ing3", i3);
        
        write.println(jsonObject.toString()); //envia o json para o servidor

        String response = reader.readLine();
        JsonNode jsonNode = mapper.readTree(response);
        String user = jsonNode.get("status").asText();
        String message = jsonNode.get("message").asText();

        System.out.println("Status: " + user);
        System.out.println("Message: " + message);
        
        reader.close();
        write.close();
        socket.close();
        return message + "\n" + user;
    }
}
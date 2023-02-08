import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TCPServer {
    private Interface interfaceGrafica;
    private AccessControl controleDeAcesso;
    private String permissao;
    private static ObjectMapper map = new ObjectMapper();
    private static ObjectNode json = map.createObjectNode();

    public TCPServer(){
        interfaceGrafica = new Interface();
        interfaceGrafica.executar();
        controleDeAcesso = new AccessControl();
        cadastrarCliente();
    }
    public static void send(String ip, String data) throws IOException {
        System.out.println(ip);
        System.out.println(data);
        json.put("Dados", data);
        //write.println(jsonObject.toString());
        //System.out.println(jsonObject.toString());
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Could not start server.");
            e.printStackTrace();
        }
    }

    public class ClientHandler extends Thread {
        private Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            String user;
            String password;
            String ip;
            String tipo;
            double ing1;
            double ing2;
            double ing3;

            try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                // Lê os dados enviados pelo cliente.
                String json = reader.readLine();
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Pedido recebido: " + json);
                JSONParser parser = new JSONParser();
                try {
                    JSONObject jsonObject = (JSONObject) parser.parse(json); // Converte o JSON para um objeto.
                    // Pega os valores do arquivo JSON.
                    user = (String) jsonObject.get("User");
                    password = (String) jsonObject.get("Password");
                    ip = (String) jsonObject.get("ipClient");
                    tipo = (String) jsonObject.get("Tipo");
                    ing1 = Double.parseDouble((String)jsonObject.get("Ing1"));
                    ing2 = Double.parseDouble((String)jsonObject.get("Ing2"));
                    ing3 = Double.parseDouble((String)jsonObject.get("Ing3"));

                    // Adiciona o pedido na fila.
                    if(controleDeAcesso.checkAccess(user, password)){
                        System.out.println("Acesso permitido!");
                        permissao = "                   Acesso permitido!";
                        if(ing1 == 0 && ing2 == 0 && ing3 == 0){
                            
                        }
                        else{
                            interfaceGrafica.addPedido(user, password,ip, ing1, ing2, ing3, tipo);
                        }
                    }else{
                        permissao = "           Usuario invalido. Acesso negado!";
                        System.out.println("Acesso negado.");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Envia uma mensagem de confirmação para o cliente.
                ObjectNode response = mapper.createObjectNode();
                response.put("status", permissao);
                response.put("message", "Pedido recebido e processado com sucesso. ");
                writer.println(response.toString());
                writer.println("Recebido.");

            } catch (IOException e) {
                System.err.println("Error handling client.");
                e.printStackTrace();
            }
        }

    }
    public void cadastrarCliente(){
        controleDeAcesso.registerUser("Jean", "12345678");
        controleDeAcesso.registerUser("Daniel", "12345678");
        controleDeAcesso.registerUser("Samuel", "12345678");
    }
}

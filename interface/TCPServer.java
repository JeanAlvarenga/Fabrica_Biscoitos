import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * A simple TCP server that accepts client connections and registers them.
 * The server will print the name of the registered client to the console.
 * The server will also send a confirmation message to the client.
 */
public class TCPServer {
    private Interface interfaceGrafica;
    private AccessControl controleDeAcesso;

    public TCPServer(){
        interfaceGrafica = new Interface();
        interfaceGrafica.executar();
        controleDeAcesso = new AccessControl();
        cadastrarCliente();
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

    private class ClientHandler extends Thread {
        private Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            String user;
            String password;
            String tipo;
            double ing1;
            double ing2;
            double ing3;

            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                // Lê os dados enviados pelo cliente.
                String json = in.readLine();
                System.out.println("Pedido recebido: " + json);
                JSONParser parser = new JSONParser();
                try {
                    JSONObject jsonObject = (JSONObject) parser.parse(json);
                    // Pega os valores do arquivo JSON.
                    user = (String) jsonObject.get("User");
                    password = (String) jsonObject.get("Password");
                    tipo = (String) jsonObject.get("Tipo");
                    ing1 = Double.parseDouble((String)jsonObject.get("Ing1"));
                    ing2 = Double.parseDouble((String)jsonObject.get("Ing2"));
                    ing3 = Double.parseDouble((String)jsonObject.get("Ing3"));

                    // Adiciona o pedido na fila.
                    if(controleDeAcesso.checkAccess(user, password)){
                        System.out.println("Acesso permitido.");
                        interfaceGrafica.addPedido(user, password, ing1, ing2, ing3, tipo);
                    }else{
                        System.out.println("Acesso negado.");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                

                //registeredClients.add(clientName);
                //System.out.println("Client send: " + clientName);
                //out.println("Registration successful.");
            } catch (IOException e) {
                System.err.println("Error handling client.");
                e.printStackTrace();
            }
        }

    }
    public void cadastrarCliente(){
        controleDeAcesso.registerUser("Jean", "12345678");
    }
}
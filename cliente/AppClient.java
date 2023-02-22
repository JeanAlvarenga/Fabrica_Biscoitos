package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AppClient {
    private static InterfaceClient interfaceCliente = new InterfaceClient();
    public static void main(String[] args) throws Exception {
        interfaceCliente.run();
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
        private String dados;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {

                String json = reader.readLine();
                System.out.println("Dado relatorio recebido: " + json);
                JSONParser parser = new JSONParser();
                try {
                    JSONObject jsonObject = (JSONObject) parser.parse(json); // Converte o JSON para um objeto.
                    // Pega os valores do arquivo JSON.
                    dados = (String) jsonObject.get("Dados");
                    interfaceCliente.addText(dados);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                } catch (IOException e) {
                    System.err.println("Error handling client.");
                    e.printStackTrace();
                }
            }
    }
}

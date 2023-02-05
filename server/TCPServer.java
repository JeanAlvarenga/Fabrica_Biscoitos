package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple TCP server that accepts client connections and registers them.
 * The server will print the name of the registered client to the console.
 * The server will also send a confirmation message to the client.
 */
public class TCPServer {
    private List<String> registeredClients = new ArrayList<>();

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.startServer();
    }

    /**
     * Starts the server and listens for client connections.
     * When a client connects, a new thread is started to handle the client.
     */
    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Could not start server.");
            e.printStackTrace();
        }
    }

    /**
     * A thread that handles a client connection.
     * The thread reads the client name from the input stream and
     * sends a confirmation message to the client.
     */
    private class ClientHandler extends Thread {
        private Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String clientName = in.readLine();
                registeredClients.add(clientName);
                System.out.println("Client registered: " + clientName);
                out.println("Registration successful.");
            } catch (IOException e) {
                System.err.println("Error handling client.");
                e.printStackTrace();
            }
        }
    }
}

/**
 * Classe principal do programa que inicia a interface gráfica.
 * @author Jean P. Alvarenga
 * @version 4.7
 */

public class AppServer {
    public static void main(String[] args){
        TCPServer server = new TCPServer();
        server.startServer();
    }
}
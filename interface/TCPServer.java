import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TCPServer {
    private Interface interfaceGrafica;
    private AccessControl controleDeAcesso;
    private String permissao;
    private static Map<String, String> pedidos = new HashMap<String, String>();
    private static List<Integer> listaDeBiscoitos = new ArrayList<Integer>();

    public TCPServer(){
        interfaceGrafica = new Interface();
        interfaceGrafica.executar();
        controleDeAcesso = new AccessControl();
        cadastrarCliente();
    }
    public static void send(String ip, String data) throws IOException {
        //System.out.println(ip);
        //System.out.println(data);
        pedidos.put(ip, data); // Adiciona os dados de produçao na lista de pedidos atraves do ip do cliente.
    }

    public static void addGrafico(int quantidadeBiscoitosProduzidos) {
        listaDeBiscoitos.add(quantidadeBiscoitosProduzidos); // Adiciona o pedido na lista de pedidos.
    }

    public String getPedidoConcluido(String usuario) {
        return pedidos.get(usuario);
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
            String requisicao;
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
                    requisicao = (String) jsonObject.get("Requisicao");
                    user = (String) jsonObject.get("User");
                    password = (String) jsonObject.get("Password");
                    ip = (String) jsonObject.get("ipClient");
                    tipo = (String) jsonObject.get("Tipo");
                    ing1 = Double.parseDouble((String)jsonObject.get("Ing1"));
                    ing2 = Double.parseDouble((String)jsonObject.get("Ing2"));
                    ing3 = Double.parseDouble((String)jsonObject.get("Ing3"));

                    ObjectNode response = mapper.createObjectNode();
                    // Adiciona o pedido na fila.
                    if(controleDeAcesso.checkAccess(user, password)){
                        System.out.println("Acesso permitido!");
                        permissao = "                   Acesso permitido!";
                        if(requisicao.equals("Pedido")){
                            interfaceGrafica.addPedido(user, password, ip, ing1, ing2, ing3, tipo);

                            // Envia uma mensagem de confirmação para o cliente.
                            
                            response.put("status", permissao);
                            response.put("message", "                   Acesso permitido!\n" + "Pedido recebido e processado com sucesso. ");
                            writer.println(response.toString());
                            writer.println("Recebido.");

                        }
                        else if(requisicao.equals("requisicao")){
                            response.put("status", "Pedido concluido");
                            String lista = "";
                            for (String key : pedidos.keySet()) {
                                if(key.equals(ip)){
                                    lista += pedidos.get(key) + "\n";
                                }
                            }
                                
                            response.put("message", lista);
                            writer.println(response.toString());
                        }
                        else if(requisicao.equals("estatistica")){
                            response.put("status", "Feito!");
                            String lista = "";
                            for(int i = 0; i < listaDeBiscoitos.size(); i++){
                                lista += listaDeBiscoitos.get(i).toString()+":";
                            }
                            response.put("message",lista);
                            writer.println(response.toString());
                        }

                    }else{
                        permissao = "Usuario invalido. Acesso negado!";
                        response.put("status", permissao);
                        response.put("message", "           Pedido recebido\n" + "Usuario invalido. Acesso negado!");
                        writer.println(response.toString());
                        System.out.println("   Acesso negado.");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                

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


/*
 * import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

Map<String, String> hashMap = new HashMap<>();
hashMap.put("key1", "value1");
hashMap.put("key2", "value2");
hashMap.put("key1", "value3");

Multimap<String, String> multimap = ArrayListMultimap.create();

// populate the Multimap with the HashMap values
for (Map.Entry<String, String> entry : hashMap.entrySet()) {
    multimap.put(entry.getKey(), entry.getValue());
}

// fetch all the elements with a given key
Collection<String> valuesForKey1 = multimap.get("key1");

 */

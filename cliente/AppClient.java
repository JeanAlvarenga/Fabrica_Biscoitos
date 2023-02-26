/**
 * Classe principal do programa que inicia a interface gráfica do cliente.
 * @author Jean P. Alvarenga
 * @version 5.0
 */

package cliente;

public class AppClient {
    private static InterfaceClient interfaceCliente = new InterfaceClient();
    public static void main(String[] args) throws Exception {
        interfaceCliente.run();
    }
    
}

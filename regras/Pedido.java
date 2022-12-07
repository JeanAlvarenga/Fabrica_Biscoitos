import java.util.concurrent.LinkedBlockingQueue;

public class Pedido {
    // Atributos
    private static int tamanhoDaFila = 0; // Tamanho da fila de pedidos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    // LinkedBlockingQueue é uma fila encadeada que permite que várias threads acessem de forma segura.
    private LinkedBlockingQueue <Biscoito> biscoitos = new LinkedBlockingQueue<Biscoito>();

    public Pedido(){
        //
    }

    /**
     * Método para adicionar biscoito no fim da fila
     * @param biscoito
     */
    public void addBiscoito(Biscoito biscoito){
        biscoitos.add(biscoito);
        tamanhoDaFila++;
    }

    /**
     * Método para desenfileirar e remover biscoito da fila
     * @return Biscoito. Primeiro biscoito da fila
     */
    public  Biscoito getBiscoito(){
        if(!biscoitos.isEmpty()){
            Biscoito b = biscoitos.poll();
            tamanhoDaFila--;
            System.out.println(b.calcularTempo());
            return b;
        }
        else{
            System.out.println("Não há biscoitos na fila");
            return null;
        }
    }

    /**
     * Método para retornar o tamanho da fila
     * @return int. Tamanho da fila
     */
    public int getTamanhoDaFila(){
        return tamanhoDaFila;
    }
}

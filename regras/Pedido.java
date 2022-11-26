import java.util.Queue;
import java.util.LinkedList;

public class Pedido {
    // Atributos
    private static int tamanhoDaFila = 0;
    private Queue <Biscoito> biscoitos;

    public Pedido(){
        biscoitos = new LinkedList<Biscoito>();
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
    public Biscoito getBiscoito(){
        Biscoito b = biscoitos.poll();
        tamanhoDaFila--;
        System.out.println(b.calcularTempo());
        return b;
    }

    /**
     * Método para retornar o tamanho da fila
     * @return int. Tamanho da fila
     */
    public int getTamanhoDaFila(){
        return tamanhoDaFila;
    }
}

import java.util.Queue;
import java.util.LinkedList;

public class Pedido {
    Queue <Biscoito> biscoitos;

    public Pedido(){
        biscoitos = new LinkedList<Biscoito>();
    }

    /**
     * Método para adicionar biscoito no fim da fila
     * @param biscoito
     */
    public void addBiscoito(Biscoito biscoito){
        biscoitos.add(biscoito);
    }

    /**
     * Método para desenfileirar e remover biscoito da fila
     * @return Biscoito. Primeiro biscoito da fila
     */
    public Biscoito getBiscoito(){
        return biscoitos.poll();
    }
}

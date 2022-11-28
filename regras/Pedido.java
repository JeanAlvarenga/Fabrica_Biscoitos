import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.LinkedList;

public class Pedido implements ActionListener {
    // Atributos
    private static int tamanhoDaFila = 0;
    private Queue <Biscoito> biscoitos = new LinkedList<Biscoito>();

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

        /**
	 * Método que é executado quando o botão é clicado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        //
    }
}

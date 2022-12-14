import java.util.concurrent.LinkedBlockingQueue;

public class Pedido {
    // Atributos
    private static int tamanhoDasFilas = 0; // Tamanho da fila de pedidos 1
    private static int tamanhoDaFila1 = 0; // Tamanho da fila de pedidos 1
    private static int tamanhoDaFila2 = 0; // Tamanho da fila de pedidos 2
    private static int tamanhoDaFila3 = 0; // Tamanho da fila de pedidos 3
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    // LinkedBlockingQueue é uma fila encadeada que permite que várias threads acessem de forma segura.
    private LinkedBlockingQueue <Biscoito> fila1 = new LinkedBlockingQueue<Biscoito>();
    private LinkedBlockingQueue <Biscoito> fila2 = new LinkedBlockingQueue<Biscoito>();
    private LinkedBlockingQueue <Biscoito> fila3 = new LinkedBlockingQueue<Biscoito>();

    public Pedido(){
        //
    }

    /**
     * Método para adicionar biscoito no fim das filas
     * @param biscoito
     */
    public void addBiscoito(Biscoito biscoito){
        if(biscoito instanceof Recheado){ // Se o biscoito for do tipo recheado
                if(fila1.size() <= fila2.size()){ // Se a fila 1 for menor ou igual a fila 2, então, adiciona na fila 1
                    fila1.add(biscoito);
                    tamanhoDaFila1++;
                }
                else{ // Se a fila 2 for menor que a fila 1, então, adiciona na fila 2
                    fila2.add(biscoito);
                    tamanhoDaFila2++;
                }
            }
        else{ // Se o biscoito for do tipo comum
            if(fila1.size() <= fila3.size() && fila1.size() <= fila3.size()){
                fila1.add(biscoito);
                tamanhoDaFila1++;
            }
            else if(fila1.size() > fila2.size() && fila3.size() >= fila2.size()){
                fila2.add(biscoito);
                tamanhoDaFila2++;
            }
            else{
                fila3.add(biscoito);
                tamanhoDaFila3++;
            }
        }
        tamanhoDasFilas++;
    }

    /**
     * Método para desenfileirar e remover biscoito da fila 1
     * @return Biscoito. Primeiro biscoito da fila
     */
    public  Biscoito getBiscoitoFila1(){
        if(!fila1.isEmpty()){
            Biscoito b = fila1.poll();
            tamanhoDaFila1--;
            tamanhoDasFilas--;
            //System.out.println(b.getId()); // Imprime o id do pedido que foi retirado da fila
            return b;
        }
        else{
            //System.out.println("Não há biscoitos na fila");
            return null;
        }
    }

    /**
     * Método para desenfileirar e remover biscoito da fila 2
     * @return Biscoito. Primeiro biscoito da fila
     */
    public  Biscoito getBiscoitoFila2(){
        if(!fila2.isEmpty()){
            Biscoito b = fila2.poll();
            tamanhoDaFila2--;
            tamanhoDasFilas--;
            //System.out.println(b.getId()); // Imprime o id do pedido que foi retirado da fila
            return b;
        }
        else{
            //System.out.println("Não há biscoitos na fila");
            return null;
        }
    }

    /**
     * Método para desenfileirar e remover biscoito da fila 3
     * @return Biscoito. Primeiro biscoito da fila
     */
    public  Biscoito getBiscoitoFila3(){
        if(!fila3.isEmpty()){
            Biscoito b = fila3.poll();
            tamanhoDaFila3--;
            tamanhoDasFilas--;
            //System.out.println(b.getId()); // Imprime o id do pedido que foi retirado da fila
            return b;
        }
        else{
            //System.out.println("Não há biscoitos na fila");
            return null;
        }
    }
    
    /**
     * Método para retornar o tamanho das filas de pedidos
     * @return int Tamanho da fila
     */
    public int getTamanhoDasFilas(){
        return tamanhoDasFilas;
    }

    /**
     * Método para retornar o tamanho da fila 1
     * @return int Tamanho da fila
     */
    public int getTamanhoDaFila1(){
        return tamanhoDaFila1;
    }

    /**
     * Método para retornar o tamanho da fila 2
     * @return int Tamanho da fila
     */
    public int getTamanhoDaFila2(){
        return tamanhoDaFila2;
    }

    /**
     * Método para retornar o tamanho da fila 3
     * @return int Tamanho da fila
     */
    public int getTamanhoDaFila3(){
        return tamanhoDaFila3;
    }
}

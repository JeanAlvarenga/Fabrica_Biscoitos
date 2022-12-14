public class Relatorio {
    // Atributos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    private static int constanteDeTempo = 1;
    private static Pedido pedido = new Pedido();

    public static void main(String[] args) {
        //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "comum" PARA ARMAZENAR fila DE PEDIDOS
        pedido.addBiscoito(new Comum(26.0, 1.0, 1.0, constanteDeTempo));
        pedido.addBiscoito(new Comum(26.0, 1.0, 1.0, constanteDeTempo));
        pedido.addBiscoito(new Comum(26.0, 1.0, 1.0, constanteDeTempo));
        pedido.addBiscoito(new Comum(26.0, 1.0, 1.0, constanteDeTempo));
        pedido.getBiscoitoFila1();
        pedido.getBiscoitoFila1();
    }
    
}

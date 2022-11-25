public abstract class Biscoito{
    // Atributos
    private final int constanteDeTempo = 1;
    private double ingrediente1, ingrediente2, ingrediente3;

    // Construtor
    public Biscoito(double ingrediente1, double ingrediente2, double ingrediente3) {
        this.ingrediente1 = ingrediente1;
        this.ingrediente2 = ingrediente2;
        this.ingrediente3 = ingrediente3;
    }

    /**
     * Método que calcula a quantidade de ingredientes necessários para a receita
     * @return quantidade de ingredientes
     */
    public double calcularQuantidadeIngredientes() {
        return ingrediente1 + ingrediente2 + ingrediente3;
    }

    /**
     * Método que retorna constante de tempo
     * @return int com a constante de tempo
     */
    public int getConstanteDeTempo() {
        return constanteDeTempo;
    }

    /**
     * Método que retorna o tempo para adicionar o ingrediente 1
     * @return double com o tempo.
     */
    public double timeIngrediente1() {
        return ingrediente1;
    }

    /**
     * Método que retorna o tempo para adicionar o ingrediente 2
     * @return double com o tempo.
     */
    public double timeIngrediente2() {
        return ingrediente2;
    }

    /**
     * Método que retorna o tempo para adicionar o ingrediente 3
     * @return double com o tempo.
     */
    public double timeIngrediente3() {
        return ingrediente3;
    }

    /**
     * Método para calcular a quantidade de biscoitos produzidos.
     * @return int quantidade de biscoitos produzidos
     */
    public abstract int calcularQuantidadeBiscoitos();

    /**
     * Método para calcular o tempo gasto na produção.
     * @return double tempo gasto na produção
     */
    public abstract double calcularTempo();
    
}
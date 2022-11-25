public class Recheado extends Biscoito{
    private double pesoBiscoito = 0.1;
     /**
     * Construtor
     */
    public Recheado(double ingrediente1, double ingrediente2, double ingrediente3, int constanteDeTempo) {
        super(ingrediente1, ingrediente2, ingrediente3, constanteDeTempo);
    }
    
    @Override
    public double calcularTempo() {
        return 1.2 * calcularQuantidadeIngredientes() * getConstanteDeTempo();
    }

    @Override
    public int calcularQuantidadeBiscoitos() {
        return (int) ((calcularQuantidadeIngredientes() / pesoBiscoito));
    }
}

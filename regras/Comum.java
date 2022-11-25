public class Comum extends Biscoito{
    private double pesoBiscoito = 0.09;
    /**
     * Construtor
     */
    public Comum(double ingrediente1, double ingrediente2, double ingrediente3) {
        super(ingrediente1, ingrediente2, ingrediente3);
    }

    @Override
    public double calcularTempo() {
        return 1.0 * calcularQuantidadeIngredientes() * getConstanteDeTempo();
    }

    @Override
    public int calcularQuantidadeBiscoitos() {
        return (int) ((calcularQuantidadeIngredientes() / pesoBiscoito));
    }
}

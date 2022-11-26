public class Comum extends Biscoito{
    private double pesoBiscoito = 0.09;
    private double cte = 1.0;
    /**
     * Construtor
     */
    public Comum(double ingrediente1, double ingrediente2, double ingrediente3, int constanteDeTempo) {
        super(ingrediente1, ingrediente2, ingrediente3, constanteDeTempo);
    }

    @Override
    public double calcularTempo() {
        return cte * calcularQuantidadeIngredientes() * getConstanteDeTempo();
    }

    @Override
    public int calcularQuantidadeBiscoitos() {
        return (int) ((calcularQuantidadeIngredientes() / pesoBiscoito));
    }
}

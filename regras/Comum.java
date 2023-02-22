public class Comum extends Biscoito{
    private double pesoBiscoito = 0.09;
    private double cte = 1.0;
    /**
     * Construtor
     */
    public Comum(String usuario, String password,String ip, double ingrediente1, double ingrediente2, double ingrediente3, int constanteDeTempo) {
        super(usuario, password, ip, ingrediente1, ingrediente2, ingrediente3, constanteDeTempo);
    }

    @Override
    public double calcularTempo() {
        return cte * calcularQuantidadeIngredientes() * getConstanteDeTempo();
    }

    @Override
    public int calcularQuantidadeBiscoitos() {
        return (int) ((calcularQuantidadeIngredientes() / pesoBiscoito));
    }

    @Override
    public String toString() {
        String s;
        int hora, minuto, segundo;
        if(calcularTempo() < 60){
            s = "tipo comum. Tempo de producao: " + (int)calcularTempo() + " seg. " + 
            calcularQuantidadeBiscoitos() + " biscoitos produzidos. "+ super.toString();
        }
        else if(calcularTempo() < 3600 && calcularTempo() >= 60){
            minuto = (int) calcularTempo() / 60;
            segundo = (int) calcularTempo() % 60;
            s = "tipo comum. Tempo de producao: " + minuto + " min " + segundo + " seg. " + 
            calcularQuantidadeBiscoitos() + " biscoitos produzidos. "+ super.toString();
        }
        else{
            hora = (int) calcularTempo() / 3600;
            minuto = (int) calcularTempo() % 3600 / 60;
            segundo = (int) calcularTempo() % 3600 % 60;
            s = "tipo comum " + "quantidade: "+ calcularQuantidadeBiscoitos() +
            "Tempo de producao: " + hora + " horas " + minuto + " min. " + segundo +
            " seg. " + calcularQuantidadeBiscoitos() + " biscoitos produzidos. "+ super.toString();
        }
        return s;
    }
}

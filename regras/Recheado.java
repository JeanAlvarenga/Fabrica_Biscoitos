public class Recheado extends Biscoito{
    private double pesoBiscoito = 0.1;
    private double cte = 1.2;
     /**
     * Construtor
     */
    public Recheado(double ingrediente1, double ingrediente2, double ingrediente3, int constanteDeTempo) {
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

    @Override
    public String toString() {
        String s;
        int hora, minuto, segundo;
        if(calcularTempo() < 60){
            s = "tipo biscoito recheado, tempo de produção: " + (int)calcularTempo() + " seg. " + 
            calcularQuantidadeBiscoitos() + " biscoitos produzidos. "+ super.toString();
        }
        else if(calcularTempo() < 3600 && calcularTempo() >= 60){
            minuto = (int) calcularTempo() / 60;
            segundo = (int) calcularTempo() % 60;
            s = "tipo biscoito recheado, tempo de produção: " + minuto + " min, " + segundo + " seg, " + 
            calcularQuantidadeBiscoitos() + " biscoitos produzidos. "+ super.toString();
        }
        else{
            hora = (int) calcularTempo() / 3600;
            minuto = (int) calcularTempo() % 3600 / 60;
            segundo = (int) calcularTempo() % 3600 % 60;
            s = "tipo biscoito recheado, " + "quantidade: "+ calcularQuantidadeBiscoitos() +
            "tempo de produção: " + hora + " horas, " + minuto + " min. " + segundo +
        " seg, " + calcularQuantidadeBiscoitos() + " biscoitos produzidos. "+ super.toString();
        }
        return s;
    }
}

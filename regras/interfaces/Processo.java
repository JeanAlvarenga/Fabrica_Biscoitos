import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Processo implements ActionListener{
    private static Pedido p;

    public Processo() {
        p = new Pedido();
    }


    	/**
	 * Método que é executado quando o botão é clicado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        p.getBiscoito();
        System.out.println(p.getTamanhoDaFila());
    }
    
}

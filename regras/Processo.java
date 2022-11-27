import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Processo implements ActionListener{
    private JLabel resultado; // Cria o label "resultado".

    private static Pedido pedido = new Pedido();
    

    public Processo(JLabel resultado) {
        this.resultado = resultado;
    }


    /**
	 * Método que é executado quando o botão é clicado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

        Biscoito b = pedido.getBiscoito();
        System.out.println(b.calcularTempo());

        String tamanhoDaFila = String.valueOf(pedido.getTamanhoDaFila());
		resultado.setText(tamanhoDaFila);
    }
    
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Processo implements ActionListener{
    private Pedido pedido;

    public Processo(Pedido pedido) {
        this.pedido = pedido;
    }


    	/**
	 * Método que é executado quando o botão é clicado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

    }
    
}

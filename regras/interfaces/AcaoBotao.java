import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AcaoBotao implements ActionListener {

	private JTextField primeiro;
	private JTextField segundo;
	private JTextField terceiro;
	private JLabel resultado;

	// Atributos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    private static int constanteDeTempo = 1;
    private static Pedido pedido = new Pedido();

	public AcaoBotao(JTextField primeiro, JTextField segundo, JTextField terceiro, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.terceiro = terceiro;
		this.resultado = resultado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "comum" PARA ARMAZENAR fila DE PEDIDOS
		double ing1 = Double.parseDouble(primeiro.getText());
		double ing2 = Double.parseDouble(segundo.getText());
		double ing3 = Double.parseDouble(terceiro.getText());
		pedido.addBiscoito(new Comum(ing1, ing2, ing3, constanteDeTempo));
		//pedido.getBiscoito();
		
		String tamanhoDaFila = String.valueOf(pedido.getTamanhoDaFila());
		resultado.setText(tamanhoDaFila);
	}

}
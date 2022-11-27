import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AcaoBotao1 implements ActionListener {

	private JComboBox tipo; 
	private JTextField primeiro; // Cria o campo de texto "primeiro".
	private JTextField segundo; // Cria o campo de texto "segundo".
	private JTextField terceiro; // Cria o campo de texto "terceiro".
	private JLabel resultado; // Cria o label "resultado".

	// Atributos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    private static int constanteDeTempo = 1;
    private static Pedido pedido = new Pedido();

	public AcaoBotao1(JComboBox tipo, JTextField primeiro, JTextField segundo, JTextField terceiro, JLabel resultado) {
		this.tipo = tipo;
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.terceiro = terceiro;
		this.resultado = resultado;
	}

	/**
	 * Método que é executado quando o botão é clicado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s;
		s = String.valueOf(tipo.getSelectedItem());
		//RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "comum" PARA ARMAZENAR fila DE PEDIDOS
		double ing1 = Double.parseDouble(primeiro.getText());
		double ing2 = Double.parseDouble(segundo.getText());
		double ing3 = Double.parseDouble(terceiro.getText());
		if(s.equals("Comum")){
			pedido.addBiscoito(new Comum(ing1, ing2, ing3, constanteDeTempo));
			System.out.println("Pedido de biscoito comum adicionado.");
		}
		else if(s.equals("Recheado")){
			pedido.addBiscoito(new Recheado(ing1, ing2, ing3, constanteDeTempo));
			System.out.println("Pedido de biscoito recheado adicionado.");
		}
		else{
			System.out.println("Nenhuma opção selecionada");
		}
		
		String tamanhoDaFila = String.valueOf(pedido.getTamanhoDaFila());
		resultado.setText(tamanhoDaFila);
	}

}
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaInterface {
	
	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Adicionar pedido");

		JLabel one = new JLabel("Ingrediente 1");
		JLabel two = new JLabel("Ingrediente 2");
		JLabel three = new JLabel("Ingrediente 3");

		JTextField primeiro = new JTextField(10);
		JTextField segundo = new JTextField(10);
		JTextField terceiro = new JTextField(10);
		JButton botao = new JButton(" Adicionar pedido ");
		JLabel resultado = new JLabel("           ?          ");
		
		//quando clica no botão será executado a classe Multiplicador
		botao.addActionListener(new AcaoBotao(primeiro, segundo, terceiro, resultado));
		
		JPanel painel = new JPanel();
		painel.add(one);
		painel.add(primeiro);
		painel.add(new JLabel("  "));
		painel.add(two);
		painel.add(segundo);
		painel.add(new JLabel("  "));
		painel.add(three);
		painel.add(terceiro);
		painel.add(botao);
		painel.add(resultado);
		
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
	}
}



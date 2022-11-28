import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame implements ActionListener {
	// Atributos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    private static int constanteDeTempo = 1;
    private static Pedido pedido = new Pedido();

    // Icone das imagens no jogo
    JFrame janela = new JFrame("Add request"); // Cria a janela com o titulo "Add request".
    private ImageIcon imFundo = new ImageIcon(getClass().getResource("fundo.png"));
    private JLabel fundo = new JLabel(imFundo);
    private int janelaW = 874, janelaH = 629;

    JLabel one = new JLabel("Ingredient 1"); // Cria o label "Ingredient 1".
	JLabel two = new JLabel("Ingredient 2"); // Cria o label "Ingredient 2".
	JLabel three = new JLabel("Ingredient 3"); // Cria o label "Ingredient 3".

	JTextField primeiro = new JTextField(10);
	JTextField segundo = new JTextField(10);
	JTextField terceiro = new JTextField(10);

	String[] tipos = { "Comum", "Recheado"};
	JComboBox<String> listaTiposBiscoitos = new JComboBox<>(tipos); // Cria a lista de tipos de biscoitos.

	JButton botao1 = new JButton(" Add request "); // Cria o botão com o texto " Adicionar pedido ".
	JButton botao2 = new JButton(" Start process "); // Cria o botão com o texto " iniciar processo ".
	JLabel texTamanhoFila = new JLabel(" Queue size: ");
	JLabel tamFila = new JLabel("           null          "); // Cria o label "null".
		

    //Quando clicamos no botão é executad o método addBiscoito da classe Pedido.
	//botao1.addActionListener(new AcaoBotao(listaTiposBiscoitos, primeiro, segundo, terceiro, tamFila));
    
    public void desenharGraficos() {
        JPanel painel = new JPanel();
        painel.setSize(janelaW, janelaH);
		painel.add(listaTiposBiscoitos);
		painel.add(one);
		painel.add(primeiro);
		painel.add(new JLabel("  "));
		painel.add(two);
		painel.add(segundo);
		painel.add(new JLabel("  "));
		painel.add(three);
		painel.add(terceiro);
		painel.add(botao1);
		painel.add(texTamanhoFila);
		painel.add(tamFila);
		painel.add(botao2);
        painel.add(fundo);
        janela.setSize(janelaW, janelaH);
        janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
    }

    public void botao(){
        //Quando clicamos no botão é executad o método addBiscoito da classe Pedido.
        botao1.addActionListener(this);

        //Quando clicamos no botão é executad o método iniciarProcesso da classe Pedido.
        botao2.addActionListener(this);
    }

	/**
	 * Método que é executado quando o botão é clicado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Trata os eventos para o botao "botao1"
		if( e.getSource() == botao1){
			String s;
			s = String.valueOf(listaTiposBiscoitos.getSelectedItem());
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
			tamFila.setText(tamanhoDaFila);
		}
		// Trata os eventos para o botao "botao2"
        if (e.getSource() == botao2){
			pedido.getBiscoito();

			String tamanhoDaFila = String.valueOf(pedido.getTamanhoDaFila());
			tamFila.setText(tamanhoDaFila);
		}
	}

    public void run(){
        desenharGraficos();
        botao();
        
    }
}

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
	// Imagens das Etapas:
	private ImageIcon imGP1 = new ImageIcon(getClass().getResource("gotapreta1.png"));
	private ImageIcon imGP2 = new ImageIcon(getClass().getResource("gotapreta2.png"));
	private ImageIcon imGP3 = new ImageIcon(getClass().getResource("gotapreta3.png"));

	private ImageIcon imGA1 = new ImageIcon(getClass().getResource("gotaAzul1.png"));
	private ImageIcon imGA2 = new ImageIcon(getClass().getResource("gotaAzul2.png"));
	private ImageIcon imGA3 = new ImageIcon(getClass().getResource("gotaAzul3.png"));

	private ImageIcon imGV1 = new ImageIcon(getClass().getResource("gotaVermelha1.png"));
	private ImageIcon imGV2 = new ImageIcon(getClass().getResource("gotaVermelha2.png"));
	private ImageIcon imGV3 = new ImageIcon(getClass().getResource("gotaVermelha3.png"));

	private ImageIcon imF1 = new ImageIcon(getClass().getResource("forno1.png"));
	private ImageIcon imF2 = new ImageIcon(getClass().getResource("forno2.png"));

	private JLabel GP1 = new JLabel(imGP1);
	private JLabel GP2 = new JLabel(imGP2);
	private JLabel GP3 = new JLabel(imGP3);

	private JLabel GA1 = new JLabel(imGA1);
	private JLabel GA2 = new JLabel(imGA2);
	private JLabel GA3 = new JLabel(imGA3);

	private JLabel GV1 = new JLabel(imGV1);
	private JLabel GV2 = new JLabel(imGV2);
	private JLabel GV3 = new JLabel(imGV3);

	private JLabel F1 = new JLabel(imF1);
	private JLabel F2 = new JLabel(imF2);

	// Imagens dos botões:
    private JLabel one = new JLabel("Ingredient 1"); // Cria o label "Ingredient 1".
	private JLabel two = new JLabel("Ingredient 2"); // Cria o label "Ingredient 2".
	private JLabel three = new JLabel("Ingredient 3"); // Cria o label "Ingredient 3".

	// Cria os campos de ingredientes.
	private JTextField primeiro = new JTextField(10);
	private JTextField segundo = new JTextField(10);
	private JTextField terceiro = new JTextField(10);

	//Cria os tipos dos biscoitos
	private String[] tipos = { "Comum", "Recheado"};
	private JComboBox<String> listaTiposBiscoitos = new JComboBox<>(tipos); // Cria a lista de tipos de biscoitos.

	// Cria os botões.
	private JButton botao1 = new JButton(" Add request "); // Cria o botão com o texto " Adicionar pedido ".
	private JButton botao2 = new JButton(" Start process "); // Cria o botão com o texto " iniciar processo ".
	private JLabel texTamanhoFila = new JLabel(" Queue size: ");
	private JLabel tamFila = new JLabel("           null          "); // Cria o label "null".
	private Canvas canvas = new Canvas();
    
	// Cria o painel.
    public void desenharGraficos() {
		
		// Configurações da janela
		canvas.add(listaTiposBiscoitos);
		canvas.add(one);
		canvas.add(primeiro);
		canvas.add(new JLabel("  "));
		canvas.add(two);
		canvas.add(segundo);
		canvas.add(new JLabel("  "));
		canvas.add(three);
		canvas.add(terceiro);
		canvas.add(botao1);
		canvas.add(texTamanhoFila);
		canvas.add(tamFila);
		canvas.add(botao2);

		janela.add(canvas);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//janela.pack();
		janela.setVisible(true);
    }

	//Método para adicionar ingrediente 1 na fila 1.
	public void exibirGotaPreta1() {
		canvas.add(GP1);
	}

	//Método para adicionar ingrediente 1 na fila 2.
	public void exibirGotaPreta2() {
		canvas.add(GP2);
	}

	//Método para adicionar ingrediente 1 na fila 3.
	public void exibirGotaPreta3() {
		canvas.add(GP3);
	}

	//Método para adicionar ingrediente 2 na fila 1.
	public void exibirGotaAzul1() {
		canvas.add(GA1);
	}

	//Método para adicionar ingrediente 2 na fila 2.
	public void exibirGotaAzul2() {
		canvas.add(GA2);
	}

	//Método para adicionar ingrediente 2 na fila 3.
	public void exibirGotaAzul3() {
		canvas.add(GA3);
	}

	//Método para adicionar ingrediente 3 na fila 1.
	public void exibirGotaVermelha1() {
		canvas.add(GV1);
	}

	//Método para adicionar ingrediente 3 na fila 2.
	public void exibirGotaVermelha2() {
		canvas.add(GV2);
	}

	//Método para adicionar ingrediente 3 na fila 3.
	public void exibirGotaVermelha3() {
		canvas.add(GV3);
	}

	//Método para indicar forno 1 ocupado.
	public void exibirForno1() {
		canvas.add(F1);
	}

	//Método para indicar forno 2 ocupado.
	public void exibirForno2() {
		canvas.add(F2);
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
		exibirGotaPreta1();
		exibirGotaPreta2();
		exibirGotaPreta3();
		exibirGotaAzul1();
		exibirGotaAzul2();
        
    }
}
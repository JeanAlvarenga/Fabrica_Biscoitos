import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Interface extends JFrame implements ActionListener {
	// Atributos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    private static int constanteDeTempo = 1;
    private static Pedido pedido = new Pedido();

    // Icone das imagens no jogo
    JFrame janela = new JFrame("Add request"); // Cria a janela com o titulo "Add request".
	// Imagens das Etapas:
	private ImageIcon imGP1 = new ImageIcon(getClass().getResource("gotapreta.png"));
	private ImageIcon imGA1 = new ImageIcon(getClass().getResource("gotaAzul.png"));
	private ImageIcon imGV1 = new ImageIcon(getClass().getResource("gotaVermelha.png"));

	private ImageIcon imF1 = new ImageIcon(getClass().getResource("forno.png"));
	private ImageIcon imF2 = new ImageIcon(getClass().getResource("forno.png"));

	private JLabel GP1 = new JLabel(imGP1);
	private JLabel GP2 = new JLabel(imGP1);
	private JLabel GP3 = new JLabel(imGP1);

	private JLabel GA1 = new JLabel(imGA1);
	private JLabel GA2 = new JLabel(imGA1);
	private JLabel GA3 = new JLabel(imGA1);

	private JLabel GV1 = new JLabel(imGV1);
	private JLabel GV2 = new JLabel(imGV1);
	private JLabel GV3 = new JLabel(imGV1);

	private JLabel F1 = new JLabel(imF1);
	private JLabel F2 = new JLabel(imF2);

	// Imagens dos botões:
	private JLabel tipo = new JLabel("Tipo:");
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
	private JButton botao2 = new JButton("  Start process  "); // Cria o botão com o texto " iniciar processo ".
	private JLabel texTamanhoFila = new JLabel(" Queue size: ");
	private JLabel tamFila = new JLabel(" 0 "); // Cria o label "null".
	private Canvas canvas = new Canvas();
    
	// Cria o painel.
    public void desenharGraficos() {
		// Configurações da janela:
		janela.setBounds(0, 0, 1117, 719);
		
		// Configurações dos labels:
		tipo.setBounds(10, 10, 30, 20);
		listaTiposBiscoitos.setBounds(50, 10, 100, 20);
		one.setBounds(160, 10, 70, 20);
		primeiro.setBounds(230, 10, 100, 20);
		two.setBounds(340, 10, 70, 20);
		segundo.setBounds(410, 10, 100, 20);
		three.setBounds(520, 10, 70, 20);
		terceiro.setBounds(590, 10, 100, 20);
		botao1.setBounds(720, 10, 110, 20);
		texTamanhoFila.setBounds(840, 10, 110, 20);
		tamFila.setBounds(950, 10, 110, 20);
		botao2.setBounds(450, 60, 160, 20);

		// Adiciona os componentes no painel:
		canvas.add(tipo);
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
		// Adiciona o painel na janela:
		janela.add(canvas);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//janela.pack(); // Ajusta o tamanho da janela para o tamanho do conteúdo.
		janela.setVisible(true);

		// Posiçoes das gotas para os eventos.
		GP1.setBounds(162, 191, 34, 33);
		GP2.setBounds(162, 415, 34, 33);
		GP3.setBounds(162, 619, 34, 33);

		GA1.setBounds(280, 191, 34, 33);
		GA2.setBounds(280, 415, 34, 33);
		GA3.setBounds(280, 619, 34, 33);

		GV1.setBounds(413, 191, 34, 33);
		GV2.setBounds(413, 415, 34, 33);
		GV3.setBounds(413, 619, 34, 33);

		F1.setBounds(630, 330, 60, 73);
		F2.setBounds(630, 540, 60, 73);
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
			botao2.setText(" Process started ");
			String tamanhoDaFila = String.valueOf(pedido.getTamanhoDaFila());
			tamFila.setText(tamanhoDaFila);
		}
	}

    public void run(){
        desenharGraficos();
        botao();

		canvas.add(GP1);
		canvas.add(GP2);
		canvas.add(GP3);
		canvas.add(GA1);
		canvas.add(GA2);
		canvas.add(GA3);
		canvas.add(GV1);
		canvas.add(GV2);
		canvas.add(GV3);
		canvas.add(F1);
		canvas.add(F2);
		
		//canvas.remove(GV2);

    }
}
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Interface extends JFrame implements ActionListener, Runnable{
	// Atributos
    //RESERVA DE MEMÓRIA E CRIAÇÃO DA VARIAVEL "pedido" PARA ARMAZENAR fila DE PEDIDOS
    private static int constanteDeTempo = 1; //____________ MODIFICAR __________________
	//private static double time; // Cria a variável "time" para armazenar o tempo sleep.
    private static Pedido pedido = new Pedido();
	//
	private static Biscoito addI1, addI2, addI3;
	private static Biscoito addI1L2, addI2L2, addI3L2;
	private static Biscoito addI1L3, addI2L3, addI3L3;
	private static Biscoito forno1;
	private static Biscoito forno2;

    // Icone das imagens
    private JFrame janela = new JFrame("Add request"); // Cria a janela com o titulo "Add request".
	private JFrame relatorio = new JFrame("Report"); // Cria a janela com o titulo "Start process".
	// Imagens das Etapas:
	private ImageIcon imGP1 = new ImageIcon(getClass().getResource("gotapreta.png"));
	private ImageIcon imGA1 = new ImageIcon(getClass().getResource("gotaAzul.png"));
	private ImageIcon imGV1 = new ImageIcon(getClass().getResource("gotaVermelha.png"));

	private ImageIcon imF1 = new ImageIcon(getClass().getResource("forno.png"));
	private ImageIcon imF2 = new ImageIcon(getClass().getResource("forno.png"));

	//Labels das imagens:
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

	// Textos antes dos ingredientes:
	private JLabel tipo = new JLabel("Tipo:");
    private JLabel one = new JLabel("Ingredient 1"); // Cria o label "Ingredient 1".
	private JLabel two = new JLabel("Ingredient 2"); // Cria o label "Ingredient 2".
	private JLabel three = new JLabel("Ingredient 3"); // Cria o label "Ingredient 3".

	// Cria os campos de ingredientes.
	private JTextField primeiro = new JTextField(10);
	private JTextField segundo = new JTextField(10);
	private JTextField terceiro = new JTextField(10);

	// Cria os campos da produção.
	private JTextField a1 = new JTextField(6);
	private JTextField a2 = new JTextField(6);
	private JTextField a3 = new JTextField(6);
	private JTextField b1 = new JTextField(6);
	private JTextField b2 = new JTextField(6);
	private JTextField b3 = new JTextField(6);
	private JTextField c1 = new JTextField(6);
	private JTextField c2 = new JTextField(6);
	private JTextField c3 = new JTextField(6);
	private JTextField d1 = new JTextField(6); // Cria o campo de texto "Forno 1".
	private JTextField d2 = new JTextField(6); // Cria o campo de texto "Forno 2".

	//Cria os tipos dos biscoitos
	private String[] tipos = { "Comum", "Recheado"};
	private JComboBox<String> listaTiposBiscoitos = new JComboBox<>(tipos); // Cria a lista de tipos de biscoitos.

	// Cria os botões.
	private JButton botao1 = new JButton(" Add request "); // Cria o botão com o texto " Adicionar pedido ".
	private JButton botao2 = new JButton("  Start process  "); // Cria o botão com o texto " iniciar processo ".
	private JButton botao3 = new JButton("  Open report "); // Cria o botão com o texto " parar processo ".
	// Cria os labels das filas:
	private JLabel texttotal = new JLabel(" Total orders: ");
	private JLabel texTamanhoFila1 = new JLabel(" Queue size 1: ");
	private JLabel texTamanhoFila2 = new JLabel(" Queue size 2: ");
	private JLabel texTamanhoFila3 = new JLabel(" Queue size 3: ");
	// Cria os labels que mostram o tamanho das filas:
	private JLabel tamTotal = new JLabel(" 0 "); // Cria o label "null".
	private JLabel tamFila1 = new JLabel(" 0 "); // Cria o label "null".
	private JLabel tamFila2 = new JLabel(" 0 "); // Cria o label "null".
	private JLabel tamFila3 = new JLabel(" 0 "); // Cria o label "null".
	// Cria o painel.
	private Canvas canvas = new Canvas();
	private CanvasRelatorio canvasRelatorio = new CanvasRelatorio();
	// Cria Area de texto.
	private JTextArea relatorioArea = new JTextArea(100, 100);
	JScrollPane scrooll = new JScrollPane(relatorioArea);

	// Cria os semáforos.
	private Semaphore semaforo1 = new Semaphore(1); // Cria o semáforo "add ingrediente 1" com 1 permissões.
	private Semaphore semaforo2 = new Semaphore(1); // Cria o semáforo "add ingrediente 2" com 1 permissões.
	private Semaphore semaforo3 = new Semaphore(1); // Cria o semáforo "add ingrediente 3" com 1 permissões.
	private Semaphore semaforo21 = new Semaphore(1); // Cria o semáforo "add ingrediente 1 linha 2" com 1 permissões.
	private Semaphore semaforo22 = new Semaphore(1); // Cria o semáforo "add ingrediente 2 linha 2" com 1 permissões.
	private Semaphore semaforo23 = new Semaphore(1); // Cria o semáforo "add ingrediente 3 linha 2" com 1 permissões.
	private Semaphore semaforo31 = new Semaphore(1); // Cria o semáforo "add ingrediente 1 linha 3" com 1 permissões.
	private Semaphore semaforo32 = new Semaphore(1); // Cria o semáforo "add ingrediente 2 linha 3" com 1 permissões.
	private Semaphore semaforo33 = new Semaphore(1); // Cria o semáforo "add ingrediente 3 linha 3" com 1 permissões.
	private Semaphore semaforoForno1 = new Semaphore(1); // Cria o semáforo "add forno1" com 1 permissões.
    private Semaphore semaforoForno2 = new Semaphore(1); // Cria o semáforo "add forno2" com 1 permissões.
	// Desenha o painel.
    private void desenharGraficos() {
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
		texttotal.setBounds(840, 10, 110, 20);
		texTamanhoFila1.setBounds(57, 192, 110, 20);
		texTamanhoFila2.setBounds(57, 415, 110, 20);
		texTamanhoFila3.setBounds(57, 615, 110, 20);
		tamTotal.setBounds(950, 10, 110, 20);
		tamFila1.setBounds(88, 210, 110, 20);
		tamFila2.setBounds(88, 434, 110, 20);
		tamFila3.setBounds(88, 634, 110, 20);
		botao2.setBounds(450, 60, 160, 20);
		botao3.setBounds(670, 60, 160, 20);
		a1.setBounds(145, 278, 70, 20);
		a2.setBounds(260, 278, 70, 20);
		a3.setBounds(395, 278, 70, 20);
		b1.setBounds(145, 500, 70, 20);
		b2.setBounds(260, 500, 70, 20);
		b3.setBounds(395, 500, 70, 20);
		c1.setBounds(145, 695, 70, 20);
		c2.setBounds(260, 695, 70, 20);
		c3.setBounds(395, 695, 70, 20);
		d1.setBounds(560, 364, 70, 20);
		d2.setBounds(560, 573, 70, 20);

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
		canvas.add(texttotal);
		canvas.add(texTamanhoFila1);
		canvas.add(texTamanhoFila2);
		canvas.add(texTamanhoFila3);
		canvas.add(tamTotal);
		canvas.add(tamFila1);
		canvas.add(tamFila2);
		canvas.add(tamFila3);
		canvas.add(botao2);
		canvas.add(botao3);
		canvas.add(a1);
		canvas.add(a2);
		canvas.add(a3);
		canvas.add(b1);
		canvas.add(b2);
		canvas.add(b3);
		canvas.add(c1);
		canvas.add(c2);
		canvas.add(c3);
		canvas.add(d1);
		canvas.add(d2);
		// Adiciona o painel na janela:
		janela.add(canvas);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//janela.pack(); // Ajusta o tamanho da janela para o tamanho do conteúdo.
		janela.setVisible(true);

		// Posiçoes das gotas que indica a ocorrencia dos eventos.
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

	//Desenhar janela de relatorio
	private void desenharGraficoRelatorio(){
		relatorio.setBounds(0, 0, 1117, 719);
		scrooll.setBounds(0, 190, 1117, 529);
		// Tamanho da barra
        scrooll.setPreferredSize(new Dimension(250, 250));
		canvasRelatorio.add(scrooll);
		relatorio.add(canvasRelatorio);
	}
	//
    private void botao(){
        //Quando clicamos no botão é executado o método addBiscoito da classe Pedido.
        botao1.addActionListener(this);

        //Quando clicamos no botão é executado o método iniciarProcesso da classe Pedido.
        botao2.addActionListener(this);

		//Quando clicamos no botão é executado o método gerar relatorio
		botao3.addActionListener(this);
    }

	/**
	 * Método que é executado quando o botão é clicado.
	 * Botão 1: Adicionar pedido.
	 * Botão 2: Iniciar processo.
	 * Botão 3: Gerar relatorio.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Trata os eventos para o botao "botao1" (Adicionar pedido).
		if(e.getSource() == botao1){
			String s; //Variavel que recebe o tipo de biscoito selecionado.
			s = String.valueOf(listaTiposBiscoitos.getSelectedItem());
			//Verifica se os campos estão preenchidos.
			if(primeiro.getText().isEmpty() || segundo.getText().isEmpty() || terceiro.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}
			else{
				try{
					double ing1 = Double.parseDouble(primeiro.getText());
					double ing2 = Double.parseDouble(segundo.getText());
					double ing3 = Double.parseDouble(terceiro.getText());
					addPedido("Usuario Local", "Autorizado", ing1, ing2, ing3, s);
					
				}
				catch(NumberFormatException ex){
					System.out.println("Erro: Algum campo não é um número.");
					return;
				}
			}
		}
		// Trata os eventos para o botao "botao2" (Iniciar processo).
        if (e.getSource() == botao2){
			botao2.setText(" Process started ");
			botao2.setBackground(Color.GREEN);
			run(); // Inicia o processo.
			
			//String tamanhoDaFilas = String.valueOf(pedido.getTamanhoDasFilas());
			//tamTotal.setText(tamanhoDaFilas);
		}
		// Trata os eventos para o botao "botao3" (Gerar relatório).
		if(e.getSource() == botao3){
            relatorio.setVisible(true);
		}
	}

	/**
	 * Método que adicionar pedido.
	 */
	public void addPedido(String usuario, String password, double ing1, double ing2, double ing3, String s){
		//Verifica se os valores são válidos.
		if(ing1 > 0 && ing2 > 0 && ing3 > 0){
			if(s.equals("Comum")){
				pedido.addBiscoito(new Comum(usuario, password, ing1, ing2, ing3, constanteDeTempo));
				System.out.println("Pedido de biscoito comum adicionado.");
			}
			else if(s.equals("Recheado")){
				pedido.addBiscoito(new Recheado(usuario, password, ing1, ing2, ing3, constanteDeTempo));
				System.out.println("Pedido de biscoito recheado adicionado.");
			}
			else{ // Nunca vai entrar aqui
				System.out.println("Nenhuma opção selecionada");
			}

		// Atualiza os valores das filas.
		String tamanhoTotal = String.valueOf(pedido.getTamanhoDasFilas());
		String tamanhoDaFila1 = String.valueOf(pedido.getTamanhoDaFila1());
		String tamanhoDaFila2 = String.valueOf(pedido.getTamanhoDaFila2());
		String tamanhoDaFila3 = String.valueOf(pedido.getTamanhoDaFila3());
		tamTotal.setText(tamanhoTotal);
		tamFila1.setText(tamanhoDaFila1);
		tamFila2.setText(tamanhoDaFila2);
		tamFila3.setText(tamanhoDaFila3);
		}
		else{
			JOptionPane.showMessageDialog(null, "Os ingredientes devem ser maiores que zero.");
		}
	}

	/**
	 * Método que inicializa o processo/threads.
	 */
	@Override
	public void run() {
		addIgrediente1Linha1();
		addIgrediente2Linha1();
		addIgrediente3Linha1();
		addIgrediente1Linha2();
		addIgrediente2Linha2();
		addIgrediente3Linha2();
		addIgrediente1Linha3();
		addIgrediente2Linha3();
		addIgrediente3Linha3();
		assar();	
			
	}

	/**
	 * Método principal.
	 */
	public void executar(){
		desenharGraficos();
		desenharGraficoRelatorio();
		botao();

	}

	/**
	 * Método que adiciona o ingrediente 1 na linha 1.
	 */
	public void addIgrediente1Linha1(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					addI1 = pedido.getBiscoitoFila1();
					String tamanhoTotal = String.valueOf(pedido.getTamanhoDasFilas());
					tamTotal.setText(tamanhoTotal);
					String tamanhoDaFila1 = String.valueOf(pedido.getTamanhoDaFila1());
					tamFila1.setText(tamanhoDaFila1);
					while(addI1 != null){
						acquire11();
						a1.setText(addI1.getId());
						canvas.add(GP1);
						janela.repaint();
						sleep(addI1.timeIngrediente1());
						acquire12();
						addI2 = addI1;
						a1.setText("");
						canvas.remove(GP1);
						janela.repaint();
						addI1 = null;
						semaforo1.release();
						sleep(0.001);
					}
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 2 na linha 1.
	 */
	public void addIgrediente2Linha1(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					while(addI2 == null){
						sleep(0.001);
					}
					a2.setText(addI2.getId());
					canvas.add(GA1);
					janela.repaint();
					sleep(addI2.timeIngrediente2());
					acquire13();
					addI3 = addI2;
					a2.setText("");
					canvas.remove(GA1);
					janela.repaint();
					addI2 = null;
					semaforo2.release();
					sleep(0.001);
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 3 na linha 1.
	 */
	public void addIgrediente3Linha1(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					while(addI3 == null){
						sleep(0.001);
					}
					a3.setText(addI3.getId());
					canvas.add(GV1);
					janela.repaint();
					sleep(addI3.timeIngrediente3());
					while(!semaforoForno1.tryAcquire()){
						sleep(0.001);
					}
					a3.setText("");
					canvas.remove(GV1);
					janela.repaint();
					sleep(0.001);
					forno1 = addI3;
					addI3 = null;
					semaforo3.release();
					
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 1 na linha 2.
	 */
	public void addIgrediente1Linha2(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					addI1L2 = pedido.getBiscoitoFila2();
					String tamanhoTotal = String.valueOf(pedido.getTamanhoDasFilas());
					tamTotal.setText(tamanhoTotal);
					String tamanhoDaFila2 = String.valueOf(pedido.getTamanhoDaFila2());
					tamFila2.setText(tamanhoDaFila2);
					
					while(addI1L2 != null){
						acquire21();
						b1.setText(addI1L2.getId());
						canvas.add(GP2);
						janela.repaint();
						sleep(addI1L2.timeIngrediente1());
						acquire22();
						addI2L2 = addI1L2;
						b1.setText("");
						canvas.remove(GP2);
						janela.repaint();
						addI1L2 = null;
						semaforo21.release();
					}
					
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 2 na linha 2.
	 */
	public void addIgrediente2Linha2(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					while(addI2L2 == null){
						sleep(0.001);
					}
					b2.setText(addI2L2.getId());
					canvas.add(GA2);
					janela.repaint();
					sleep(addI2L2.timeIngrediente2());
					acquire23();
					addI3L2 = addI2L2;
					b2.setText("");
					canvas.remove(GA2);
					janela.repaint();
					addI2L2 = null;
					semaforo22.release();
					sleep(0.001);
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 3 na linha 2.
	 */
	public void addIgrediente3Linha2(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					while(addI3L2 == null){
						sleep(0.001);
					}
					b3.setText(addI3L2.getId());
					canvas.add(GV2);
					janela.repaint();
					sleep(addI3L2.timeIngrediente3()); // Aguarda o tempo de preparo do ingrediente 3.

					boolean ocupado = false;
					while(!ocupado){ //Verifica se o forno está ocupado.
						if(semaforoForno1.tryAcquire()){
							forno1 = addI3L2;
							addI3L2 = null;
							semaforo23.release();
							ocupado = true;
						}
						else if(semaforoForno2.tryAcquire()){
							forno2 = addI3L2;
							addI3L2 = null;
							semaforo23.release();
							ocupado = true;
						}
						else{
							sleep(0.001);
						}
					}
					sleep(0.001);
					b3.setText("");
					canvas.remove(GV2);
					janela.repaint();
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 1 na linha 3.
	 */
	public void addIgrediente1Linha3(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					addI1L3 = pedido.getBiscoitoFila3();
					String tamanhoTotal = String.valueOf(pedido.getTamanhoDasFilas());
					tamTotal.setText(tamanhoTotal);
					String tamanhoDaFila3 = String.valueOf(pedido.getTamanhoDaFila3());
					tamFila3.setText(tamanhoDaFila3);
					
					while(addI1L3 != null){
						acquire31();
						c1.setText(addI1L3.getId());
						canvas.add(GP3);
						janela.repaint();
						sleep(addI1L3.timeIngrediente1());
						acquire32();
						addI2L3 = addI1L3;
						c1.setText("");
						canvas.remove(GP3);
						janela.repaint();
						addI1L3 = null;
						semaforo31.release();
					}
					
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 2 na linha 3.
	 */
	public void addIgrediente2Linha3(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					while(addI2L3 == null){
						sleep(0.001);
					}
					c2.setText(addI2L3.getId());
					canvas.add(GA3);
					janela.repaint();
					sleep(addI2L3.timeIngrediente2());
					acquire33();
					addI3L3 = addI2L3;
					c2.setText("");
					canvas.remove(GA3);
					janela.repaint();
					addI2L3 = null;
					semaforo32.release();
					sleep(0.001);
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona o ingrediente 3 na linha 3.
	 */
	public void addIgrediente3Linha3(){
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					while(addI3L3 == null){
						sleep(0.001);
					}
					c3.setText(addI3L3.getId());
					canvas.add(GV3);
					janela.repaint();
					sleep(addI3L3.timeIngrediente3());
					while(!semaforoForno2.tryAcquire()){
						sleep(0.001);
					}
					c3.setText("");
					canvas.remove(GV3);
					janela.repaint();
					sleep(0.001);
					forno2 = addI3L3;
					addI3L3 = null;
					semaforo33.release();
				}
			}
			
		}).start();
	}

	/**
	 * Método que adiciona biscoito no forno.
	 */
	public void assar(){
			new Thread(new Runnable() {
				@Override
				public void run(){
					while(true){
						while(forno1 == null){
							sleep(0.001);
						}
						d1.setText(forno1.getId());
						canvas.add(F1);
						janela.repaint();
						sleep(forno1.calcularTempo());
						d1.setText("");
						canvas.remove(F1);
						janela.repaint();
						String s = (forno1.getId() + ", cliente: " + forno1.getUsuario() + " , fabricado forno 1, " + forno1); // Imprime o toString do objeto.
						relatorioArea.append(s + "\n");
						forno1 = null;
						semaforoForno1.release();
						
						
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run(){
					while(true){
						while(forno2 == null){
							sleep(0.001);
						}
						d2.setText(forno2.getId());
						canvas.add(F2);
						janela.repaint();
						sleep(forno2.calcularTempo());
						d2.setText("");
						canvas.remove(F2);
						janela.repaint();
						String a = (forno2.getId() + ", cliente: " + forno2.getUsuario() + " , fabricado forno 2, " + forno2); // Imprime o toString do objeto.
						relatorioArea.append(a + "\n");
						forno2 = null;
						semaforoForno2.release();
						
					}
				}
			}).start();
	}

	/**
	 * Método que simula o tempo de espera.
	 * @param time do tipo double.
	 */
	public void sleep(double time) {
		try {
			Thread.sleep((long) (time * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}

	/**
	 * Método que tenta passa pelo semaforo 1 e caso não consiga, ele espera.
	 */
	private void acquire11() {
		try {
		  semaforo1.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	 /**
	 * Método que tenta passa pelo semaforo 2 e caso não consiga, ele espera.
	 */
	  private void acquire12() {
		try {
		  semaforo2.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 3 e caso não consiga, ele espera.
	 */
	  private void acquire13() {
		try {
		  semaforo3.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 4 e caso não consiga, ele espera.
	 */
	  private void acquire21() {
		try {
		  semaforo21.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 5 e caso não consiga, ele espera.
	 */
	  private void acquire22() {
		try {
		  semaforo22.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 6 e caso não consiga, ele espera.
	 */
	  private void acquire23() {
		try {
		  semaforo23.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 7 e caso não consiga, ele espera.
	 */
	  private void acquire31() {
		try {
		  semaforo31.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 8 e caso não consiga, ele espera.
	 */
	  private void acquire32() {
		try {
		  semaforo32.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }

	/**
	 * Método que tenta passa pelo semaforo 9 e caso não consiga, ele espera.
	 */
	  private void acquire33() {
		try {
		  semaforo33.acquire();
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		  e.printStackTrace();
		}
	  }
}
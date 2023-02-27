/**
 * Classe responsável por criar a interface do cliente.
 * @author Jean P. Alvarenga
 * @version 5.0
 */


package cliente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class InterfaceClient extends JFrame implements ActionListener {

    private JFrame janela = new JFrame("Add request (Client)");
    private JFrame janelaGrafico = new JFrame("Fetch report");
    // Textos antes dos ingredientes:
	private JLabel tipo = new JLabel("Tipo:");
    private JLabel one = new JLabel("Ingredient 1"); // Cria o label "Ingredient 1".
	private JLabel two = new JLabel("Ingredient 2"); // Cria o label "Ingredient 2".
	private JLabel three = new JLabel("Ingredient 3"); // Cria o label "Ingredient 3".
    private JLabel client = new JLabel("Client:");
    private JLabel password = new JLabel("Password:");
    private JLabel ip = new JLabel("IP Server");

	// Cria os campos de ingredientes.
    private String[] tipos = { "Comum", "Recheado"}; // Cria um array de strings com os tipos de biscoitos.
    private JComboBox<String> listaTiposBiscoitos = new JComboBox<>(tipos); // Cria a lista de tipos de biscoitos.
	private JTextField primeiro = new JTextField(10);
	private JTextField segundo = new JTextField(10);
	private JTextField terceiro = new JTextField(10);
    private JTextField clientField = new JTextField(10);
    private JTextField passwordField = new JTextField(10);
    private JTextField ipField = new JTextField(10);

    private JTextArea relatorioArea = new JTextArea(100, 100);
    JScrollPane scrooll = new JScrollPane(relatorioArea);

    // Cria os botões.
	private JButton botao1 = new JButton(" Add request "); // Cria o botão com o texto " Adicionar pedido ".
	private JButton botao2 = new JButton(" Fetch report "); // Cria o botão com o texto "burcar relatório"
    private JButton botao3 = new JButton(" Statistic "); // Cria o botão com o texto "burcar relatório"

    private CanvasCliente canvasCliente = new CanvasCliente();
    private TCPClient tcpClient;

    public void desenharGraficos() {
        // Configurações da janela:
		janela.setBounds(0, 0, 1177, 719);
        tipo.setBounds(10, 10, 30, 20);
        listaTiposBiscoitos.setBounds(50, 10, 100, 20);
		one.setBounds(160, 10, 70, 20);
		primeiro.setBounds(230, 10, 100, 20);
		two.setBounds(340, 10, 70, 20);
		segundo.setBounds(410, 10, 100, 20);
		three.setBounds(520, 10, 70, 20);
		terceiro.setBounds(590, 10, 100, 20);
        client.setBounds(10, 40, 50, 20);
        clientField.setBounds(50, 40, 100, 20);
        password.setBounds(160, 40, 70, 20);
        passwordField.setBounds(230, 40, 100, 20);
        ip.setBounds(340, 40, 70, 20);
        ipField.setBounds(410, 40, 100, 20);
		botao1.setBounds(720, 10, 110, 20);
        botao2.setBounds(720, 40, 110, 20);
        botao3.setBounds(840, 10, 110, 20);
        scrooll.setBounds(0, 210, 1177, 529);
		// Tamanho da barra
        scrooll.setPreferredSize(new Dimension(250, 250));

        // Adiciona os componentes no painel:
        canvasCliente.add(tipo);
        canvasCliente.add(listaTiposBiscoitos);
        canvasCliente.add(one);
        canvasCliente.add(primeiro);
        canvasCliente.add(two);
        canvasCliente.add(segundo);
        canvasCliente.add(three);
        canvasCliente.add(terceiro);
        canvasCliente.add(botao1);
        canvasCliente.add(botao2);
        canvasCliente.add(botao3);
        canvasCliente.add(client);
        canvasCliente.add(clientField);
        canvasCliente.add(password);
        canvasCliente.add(passwordField);
        canvasCliente.add(ip);
        canvasCliente.add(ipField);
        canvasCliente.add(scrooll);

        // Adiciona o painel na janela:
        janela.add(canvasCliente);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.setVisible(true);
    }

    public void botao(){
        //Quando clicamos no botão é executado o método addBiscoito da classe Pedido.
        botao1.addActionListener(this);
        botao2.addActionListener(this);
        botao3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botao1){
            String status;
            String s; //Variavel que recebe o tipo de biscoito selecionado.
			s = String.valueOf(listaTiposBiscoitos.getSelectedItem());
			//Verifica se os campos estão preenchidos.
			if(primeiro.getText().isEmpty() || segundo.getText().isEmpty() || terceiro.getText().isEmpty() || clientField.getText().isEmpty() || passwordField.getText().isEmpty() || ipField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}
			else{
				try{
                    String ip = ipField.getText();
                    String client = clientField.getText();
                    String password = passwordField.getText();
					String ing1 = primeiro.getText();
					String ing2 = segundo.getText();
					String ing3 = terceiro.getText();
					
                    status = tcpClient.createJSON("Pedido", ip, client, password, s, ing1, ing2, ing3);

                    JOptionPane.showMessageDialog(null, status);
					
				}
				catch(NumberFormatException ex){
					System.out.println("Erro: Algum campo não é um número.");
					return;
				} catch (Exception e1) {
                    e1.printStackTrace();
                }
			}
		}  
        else if(e.getSource() == botao2){
            String ip = ipField.getText();
            String client = clientField.getText();
            String password = passwordField.getText();
            String status;
            try {
                status = tcpClient.createJSON("requisicao", ip, client, password, "0", "0", "0", "0");
                 relatorioArea.setText("");
                 String[] valores = status.split(",");
                for(int i = 0; i < valores.length; i++){
                        relatorioArea.append(valores[i] +"\n");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }    
        else if(e.getSource() == botao3){
            String ip = ipField.getText();
            String client = clientField.getText();
            String password = passwordField.getText();
            String producao, pedido = null;
            double tempoDeProducao = 0;
            DefaultCategoryDataset barra = new DefaultCategoryDataset(); //Cria um objeto do tipo DefaultCategoryDataset.
    
            desenharJannelaGrafico();
            try {
                producao = tcpClient.createJSON("estatistica", ip, client, password, "0", "0", "0", "0");
                // System.out.println("Chegou: "+ producao);
                String[] producaoSplit = producao.split(";");
                for(int i = 0; i < producaoSplit.length; i++){
                    String[] producaoSplit2 = producaoSplit[i].split("#");
                    //System.out.println("Tamanho: " + producaoSplit2.length);
                    for(int j = 0; j < producaoSplit2.length; j++){
                        //System.out.println("Producao: " + producaoSplit2[j]);
                        if(j == 0){
                            pedido = producaoSplit2[j];
                            //System.out.println("Pedido: " + pedido);
                        }
                        else if(j == 1){
                            tempoDeProducao = Double.parseDouble(producaoSplit2[j]);
                            //System.out.println("Tempo de Producao: " + tempoDeProducao);
                        }
                        barra.setValue(tempoDeProducao, pedido, "");
                        
                    }
                }
                JFreeChart grafico = ChartFactory.createBarChart3D("Relatório de Tendência de Produção", "Pedidos", "Quantidade de Biscoitos Fabricados", barra, PlotOrientation.VERTICAL, true, true, false); //Cria o gráfico de barras.
                ChartPanel painel = new ChartPanel(grafico); //Cria um painel para o gráfico.
                janelaGrafico.add(painel); //Adiciona o painel na janela.

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            //criarGrafico();
            janelaGrafico.setVisible(true);
            
        } 
    }

    public void addText(String text){
        relatorioArea.append(text);
    }

    public void run(){
        desenharGraficos();
        desenharJannelaGrafico();
        botao();
        tcpClient = new TCPClient();
    }

    public void desenharJannelaGrafico(){
        janelaGrafico.removeAll();
        janelaGrafico.repaint();
        janelaGrafico = new JFrame("Statistic");
        janelaGrafico.setSize(950, 600);
        janelaGrafico.setLocationRelativeTo(null);
    }  
}

/**
 * Classe responsável por criar o painel de fundo da interface do relatório do servidor.
 * @author Jean P. Alvarenga
 * @version 5.0
 */

import javax.swing.JPanel;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CanvasRelatorio extends JPanel {
    private BufferedImage image;
    public CanvasRelatorio(){
        super.setLayout(null);
        try{
            //image = ImageIO.read(new File("C:\\Users\\jeana\\OneDrive\\documentos\\GitHub\\Fabrica_Biscoitos\\imagens\\fundoRelatorio.png"));
            image = ImageIO.read(new File("D:\\documentos\\GitHub\\Fabrica_Biscoitos\\imagens\\fundoRelatorio.png"));
        }catch(Exception e){
            System.out.println("Erro ao carregar imagem");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(1177, 719, 0), 0, 0, null);
    }
}

import javax.swing.JPanel;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    BufferedImage image;

    public Canvas(){
        try{
            image = ImageIO.read(new File("D:\\documentos\\GitHub\\Fabrica_Biscoitos\\imagens\\fundo1.png"));
        }catch(Exception e){
            System.out.println("Erro ao carregar imagem");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(1117, 719, 0), 0, 0, null);
    }
}

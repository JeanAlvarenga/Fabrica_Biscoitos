import javax.swing.JPanel;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private BufferedImage image;

    public Canvas(){
        super.setLayout(null);
        try{
            //image = ImageIO.read(new File("C:\\Users\\jeana\\OneDrive\\documentos\\GitHub\\Fabrica_Biscoitos\\imagens\\fundo2.png"));
            image = ImageIO.read(new File("D:\\documentos\\GitHub\\Fabrica_Biscoitos\\imagens\\fundo2.png"));
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
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet extends JFrame {
    int sizeY = 85;
    int sizeX = 90;
    BufferedImage img;

    public static void main(String[] args) {
        SpriteSheet sheet = new SpriteSheet("Dragon.png");
        sheet.setDefaultCloseOperation(2);
        sheet.setBounds(10, 10, 600, 200);
        sheet.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < 8; i++) {
            g.drawImage(getImage(i),i*sizeX,50,null);
        }
    }

    public SpriteSheet(String imagePath) {
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public BufferedImage getImage(int course) {
        if (course < 5) {
            return img.getSubimage(sizeX * course, 0,sizeX, sizeY);
        } else {
            course = 8 - course;
            BufferedImage subImg = img.getSubimage(sizeX * course, 0,sizeX, sizeY);
            BufferedImage bi = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();
            g.drawImage(subImg, sizeX,0,0,sizeY,0,0,sizeX,sizeY,null);
            return bi;
        }
    }
}

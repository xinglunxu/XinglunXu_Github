import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


/**
 *
 * @author xinglunxu
 */
public class View extends JPanel {
    private Game game;
    private Image image;
    
@Override
public void paintComponent(Graphics graphics)
{
    super.paintComponent(graphics);
    graphics.drawImage( image, 0, 0, Game.IMAGE_SIZE, Game.IMAGE_SIZE, this );
}

View(Game game, Image image){this.game = game; this.image = image;}
    
    
}

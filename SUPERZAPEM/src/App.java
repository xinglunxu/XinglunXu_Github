import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Xinglun Xu
 */
public class App extends JFrame
{
    private final ControlPanel controlPanel;
    private final Game game;
    private final View view;
    
    App() 
    {        
        game = new Game();
        view = new View( game, game.getImage() );
        controlPanel = new ControlPanel( view, game ); 
        
        setTitle( "Zap 'em" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                      
        add( view, BorderLayout.CENTER );
        add( controlPanel, BorderLayout.SOUTH );
        
        Dimension dimension = new Dimension( Game.IMAGE_SIZE, Game.IMAGE_SIZE + controlPanel.getHeight() );
        setSize( dimension  );
        setPreferredSize( dimension );
        setVisible( true );
    }
    
    /**
     * Run the Graphics Virtual Machine application.
     * @param args unused 
     */
    public static void main( String[] args ) { App app = new App(); }
}
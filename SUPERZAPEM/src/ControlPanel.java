
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 *
 * @author Xinglun Xu
 */
public class ControlPanel extends JPanel implements ActionListener, MouseListener
{
    private final static int PAUSE = 150; // milliseconds
    
    private final View view;
    private final Game game;
    
    private final JButton newGameButton = new JButton( "New Game" );
    private final JLabel  gameDurationLabel = new JLabel( "  Game duraion in seconds:" );
    private final JTextField gameDurationTextField = new JTextField();
    private final JLabel gameLevelLabel = new JLabel("Game Level");
    private final JTextField gameLevelTextField = new JTextField();
    
    private final Timer animationTimer;
    private long gameStartTime;
    
    ControlPanel( View view, Game game ) 
    {
        this.view = view;
        this.game = game;
     
        setLayout( new GridLayout( 1, 5 ) );
        add(gameLevelLabel);
        add(gameLevelTextField);
        add( newGameButton );
        add( gameDurationLabel );
        add( gameDurationTextField );

        animationTimer = new Timer( PAUSE, this );
        gameDurationTextField.setEditable( false );
        initialize();
    }

    private void initialize() 
    {
        //------------------------------------------
        // contoller TEMPLATE CODE for each action
        //------------------------------------------
        // If you are running Java 8, use lambda expressions
//        newGameButton.addActionListener( ( java.awt.event.ActionEvent actionEvent ) -> { newGameButtonActionPerformed( actionEvent ); } );
//       
        // If you are not running Java 8, uncomment the code below   
        newGameButton.addActionListener( new ActionListener() 
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) 
            {
                newGameButtonActionPerformed( actionEvent );
            }
        });
        
        // register this as the listener for mouse events in the View JPanel
        view.addMouseListener( this );
    }

    // _____________________________
    //  controller for each action
    // _____________________________
    private void newGameButtonActionPerformed( ActionEvent actionEvent ) 
    {
        // set the text field to the empty string;
        gameDurationTextField.setText("");
        
        // record the start time of the game;
        gameStartTime = System.currentTimeMillis();
        
        // restart the Timer
        animationTimer.restart();
        
        // start the game. 
        game.start();
        
        
    }
    /**
     * Implementation of ActionListener of Timer
     * @param e unused 
     */
    @Override
    public void actionPerformed( ActionEvent e) 
    {
        // 1. move and draw the critters (after blanking their previous positions)
        game.draw();
        // 2. repaint the View
        view.repaint();
        // 3. if the game is over, 
        //    a. stop the Timer
        //    b. get the current time, using System.currentTimeMillis
        //    c. compute the duration of the game
        //    d. set the TextField to this value, in seconds, with 3 significant digits to the right of the decimal point.
        
        if(game.getGameState())
        {
            animationTimer.stop();
            long gameTime = (System.currentTimeMillis() - gameStartTime)/1000;
            DecimalFormat myFormat = new DecimalFormat("0.000");
            gameDurationTextField.setText(myFormat.format(gameTime));
        }
        
    }

    // implementation of MouseListener
    @Override
    public void mouseClicked( MouseEvent event ) 
    {
        // your implementation of a listener for mouse click events goes here:
        // 1. stop the timer;
        animationTimer.stop();
        // 2. invoke the Game.processClick method with the x & y coordinates of the mouse click;
        game.processClick(event.getX(), event.getY());
        // 3. start the timer;
        animationTimer.start();
        // 4. repaint the view component.
        view.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
}

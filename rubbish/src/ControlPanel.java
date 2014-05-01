

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ControlPanel extends JPanel implements ActionListener, MouseListener
{
	private final static int PAUSE = 150; // milliseconds
	private final View view;
	private final Game game;
	
	private final JButton newGameButton= new JButton("New Game");
	private final JLabel  gameDurationLabel = new JLabel( "  Game duraion in seconds:" );
	private final JTextField gameDurationTextField = new JTextField();
	
	private final Timer animationTimer;
    private long gameStartTime;
    
    ControlPanel( View view, Game game ) 
    {
        this.view = view;
        this.game = game;
     
        setLayout( new GridLayout( 1, 3 ) );
        add( newGameButton );
        add( gameDurationLabel );
        add( gameDurationTextField );

        animationTimer = new Timer( PAUSE, this );//------
        gameDurationTextField.setEditable( false );
        initialize();
    }

    private void initialize() 
    {   
        newGameButton.addActionListener( new ActionListener() 
        {
            //@Override
            public void actionPerformed( ActionEvent actionEvent ) 
            {
                newGameButtonActionPerformed( actionEvent );
            }
        });
        
        view.addMouseListener( this );
    }
    
    private void newGameButtonActionPerformed( ActionEvent actionEvent ) 
    {
        // set the text field to the empty string;
        // record the start time of the game;
        // restart the Timer
        // start the game. 
    	gameDurationTextField.setText("");
    	gameStartTime = System.currentTimeMillis();
    	animationTimer.restart();
    	game.start();
    }


	public void actionPerformed( ActionEvent e) {
		game.draw();
		view.repaint();
		if (game.isGameOver()){
			animationTimer.stop();
			long currentTime = System.currentTimeMillis();
			double duration = Math.round((gameStartTime - currentTime)*1000)/1000;
			gameDurationTextField.setText(""+duration);
		}
		
    }

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		animationTimer.stop();
		game.processClick(e.getX(),e.getY());
		animationTimer.start();
		view.repaint();
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

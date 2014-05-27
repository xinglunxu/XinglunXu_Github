import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * A JFrame that integrates the components of the game.
 * @author Jiapei Yao, Xinglun, Xu
 *
 */
public class App extends JFrame {
	private final View view = new View();
	private final ControlPanel controlPanel;
	private final GVM gvm;
	
	App(){
		gvm = new GVM();
		controlPanel = new ControlPanel( view, gvm );        
		setTitle( "GALA" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		Dimension dimension = new Dimension( GVM.IMAGE_SIZE, GVM.IMAGE_SIZE + controlPanel.getHeight() );
		setSize( dimension  );
		setPreferredSize( dimension );
		
		JScrollPane ScrollView= new JScrollPane(view);
		add( ScrollView, BorderLayout.CENTER );
		add( controlPanel, BorderLayout.PAGE_START );
		add( controlPanel.getTextPanel(), BorderLayout.PAGE_END );
		
		setVisible( true );
		
	}
	
	/**
	 * Instantiate a game.
	 * @param args unused
	 */
	public static void main( String[] args ) { App app = new App(); }
}

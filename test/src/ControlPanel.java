
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The controller for the game, integrate all elements of the game
 * @author Jiapei Yao, Xinglun Xu
 *
 */
public class ControlPanel extends JPanel {
	private final View view;
	private final GVM gvm;
	
	private final JButton loadButton = new JButton("Load");
	private final JButton runButton = new JButton("Run");
	private final JButton stepButton = new JButton("Step");
	
	private final JTextArea LeftArea = new JTextArea(6,0);
	private final JTextArea RightArea= new JTextArea(6,0);
	private final JScrollPane ScrollLeftArea = new JScrollPane(LeftArea);
	private final JScrollPane ScrollRightArea = new JScrollPane(RightArea);
	private final JPanel TextPanel = new JPanel();
	
	ControlPanel( View view, GVM gvm ){
		this.view = view;
		this.gvm = gvm;
		
		setLayout( new GridLayout( 1, 3 ) );
		add( loadButton );
		add( runButton );
		add( stepButton );
		
		runButton.setEnabled(false);
		stepButton.setEnabled(false);
		 
		initialize();
		view.setImage( gvm.getImage() );
		}
	
	private void initialize(){
		loadButton.addActionListener( new ActionListener() {
		    //@Override
			public void actionPerformed( ActionEvent actionEvent )
			{ loadButtonActionPerformed( actionEvent ); }
		});
		runButton.addActionListener( new ActionListener() {
		    //@Override
			public void actionPerformed( ActionEvent actionEvent )
			{ runButtonActionPerformed( actionEvent ); }
		});
		
		stepButton.addActionListener( new ActionListener() {
			 //@Override
			public void actionPerformed( ActionEvent actionEvent )
			{ stepButtonActionPerformed( actionEvent ); }
		});
	}
	
	/**
     * Implementation of load Button
     * @param actionEvent unused
     */
	private void loadButtonActionPerformed( ActionEvent actionEvent ) {
		gvm.load();
		view.repaint();
		LeftArea.setText(gvm.MESSAGE_ON_LEFT);
		RightArea.setText(gvm.ERROR_ON_RIGHT);
		if (gvm.ASSEMBLY_SUCCESSFUL){
			stepButton.setEnabled(true);
			runButton.setEnabled(true);
			loadButton.setEnabled(false);
		}
	}
	
	/**
     * Implementation of run Button
     * @param actionEvent unused
     */
	private void runButtonActionPerformed( ActionEvent actionEvent ) {
		gvm.run();
	    view.repaint();
	    loadButton.setEnabled(true);
	    runButton.setEnabled(false);
		stepButton.setEnabled(false);
	}
	
	/**
     * Implementation of step Button
     * @param actionEvent unused
     */
	private void stepButtonActionPerformed( ActionEvent actionEvent ) {
		gvm.step();
		view.repaint();
		if (gvm.STOPSIGNAL){
			loadButton.setEnabled(true);
			runButton.setEnabled(false);
			stepButton.setEnabled(false);
		}
	}
	
	/**
	 * pass the JPanel consist of two JTextArea to outer program
	 * @return TextPanel
	 */
	public JPanel getTextPanel(){
		TextPanel.setLayout(new GridLayout(1,2));
		LeftArea.setEditable(false);
		RightArea.setEditable(false);
		TextPanel.add(ScrollLeftArea);
		TextPanel.add(ScrollRightArea);
		return TextPanel;
	}
}

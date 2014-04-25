import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Controller extends JPanel
{
    private final JButton buttonOk = new JButton("OK");
    private final JButton buttonCancel = new JButton("Cancel");
    private final JButton buttonSetup = new JButton("Setup");
    private final JButton buttonHelp = new JButton("Help");
    private final JLabel labelInput = new JLabel("Printer:", SwingConstants.RIGHT);
    private final JLabel labelOutput = new JLabel("Console:",SwingConstants.RIGHT);
    private final JLabel labelQuality = new JLabel("Print Quality",SwingConstants.RIGHT);
    private final JTextField textFieldInput = new JTextField();
    private final JTextField textFieldOutput = new JTextField();
    private final Checkbox checkBoxToFile = new Checkbox("Print to file");
    private final Checkbox checkBoxDoubleSide = new Checkbox("2-sided");
    private final JRadioButton radioSelection = new JRadioButton("Selection");
    private final JRadioButton radioAll = new JRadioButton("All");
    private final JComboBox comboBox = new JComboBox();
    
    private final JPanel inputController;
    private final JPanel outputController;
    private final GridBagConstraints gbc1, gbc2;

    Controller()
    {
        inputController = new JPanel();
        inputController.setLayout(new GridLayout(4,3));
        outputController = new JPanel();
        outputController.setLayout(new GridBagLayout());

           
        inputController.add(labelInput);
        inputController.add(textFieldInput);
        inputController.add(buttonOk);
        inputController.add(checkBoxToFile);
        inputController.add(radioSelection);
        inputController.add(buttonCancel);
        inputController.add(checkBoxDoubleSide);
        inputController.add(radioAll);
        inputController.add(buttonSetup);
        inputController.add(labelQuality);
        inputController.add(comboBox);
        inputController.add(buttonHelp);
        
        
        gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.weightx = 0.025;
        gbc1.weighty = 1;
        outputController.add(labelOutput,gbc1);
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        gbc1.weightx = 1;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        outputController.add(textFieldOutput,gbc1);
        
        
        setLayout(new GridBagLayout());
        gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        add(inputController,gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.weighty = 0.25;
        gbc2.weightx = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        add(outputController,gbc2);
        

    }

}
package memory.ihm;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JDialog;



public class DialogDebut extends JDialog {
	
	JPanel panelF = new JPanel();
	JPanel panelMode = new JPanel();
	JPanel panelNbCarte = new JPanel();
	JPanel panelTitre = new JPanel();
    JButton suite = new JButton("next");
    FrameMemory f = new FrameMemory();

	
	public DialogDebut(JFrame parent, String title, String message) {
        super(parent, title);
        
        //panel
        this.add(panelF);
        this.add(panelNbCarte);
        this.add(panelMode);
        this.add(panelTitre);
        
        //buton
        this.panelF.add(suite);
        
        
        
        
        
        
	}
        
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.nextt) {
			this.f.setVisible(true);
			this.dispose();
			
		}

}
}
package memory.ihm;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class DialogDebut extends JDialog {
	

	private static final long serialVersionUID = 2817904133416313186L;
	
	JPanel panelF;
	JPanel panelMode;
	JPanel panelNbCarte;
	JPanel panelTitre;
	JPanel panel;
	 JButton suite;
	 JButton btnCommencerPartie;

    public JButton getBtnCommencerPartie() {
		return this.btnCommencerPartie;
	}

	public void setBtnCommencerPartie(JButton btnCommencerPartie) {
		this.btnCommencerPartie = btnCommencerPartie;
	}

	FrameMemory f = new FrameMemory();

	
	public DialogDebut(JFrame parent, String title, String message) {	
        super(parent, title);

    	 panelF = new JPanel();
    	 panelMode = new JPanel();
    	 panelNbCarte = new JPanel();
    	 panelTitre = new JPanel();		
    	 panel = new JPanel();

         this.suite = new JButton("next");			
        this.btnCommencerPartie = new JButton("commencer partie");

        //panel
        this.add(this.panelF);
        this.add(this.panelNbCarte);
        this.add(this.panelMode);
        this.add(this.panelTitre);
        
        //buton
        panelF.add(this.suite);
        this.setSize(400,400);
        this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.panel.add(btnCommencerPartie);
		
		 System.out.println(this);
		
		getContentPane().add(panel);
		{
			panel.add(btnCommencerPartie);
		}

    
	}
package memory.ihm;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import memory.ihm.FrameMemory;
public class Main {

	public static void main(String[] args) {
		

		FrameMemory memory = new FrameMemory();
			
		
		
		DialogDebut debut = new DialogDebut(memory, "Mon Memory", "message");
		
		debut.getBtnCommencerPartie().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				System.out.println("eee");
				
				memory.pack();
				
				memory.setVisible(true);
				debut.dispose();
			}
		});
		
		debut.setVisible(true);
		
		
		
		
		

		}

}

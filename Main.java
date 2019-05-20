package memory.ihm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		FrameMemory memory = new FrameMemory();
		//memory.setVisible(true);
		
		FrameMemory debut = new FrameMemory();
		
	/*debut.getBtnCommencerPartie().addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {

			memory.pack();
			memory.setVisible(true);
			debut.dispose();
			
			}
		});*/
		
		debut.setVisible(true);

	}
}
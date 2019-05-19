package memory.ihm;
 import memory.ihm.FrameMemory;
public class Main {

	public static void main(String[] args) {

		
		FrameMemory memory = new FrameMemory();
		//memory.setVisible(true);
		
		DialogDebut debut = new DialogDebut(memory, "Mon Memory", "message");
		debut.setVisible(true);
		
		}

}

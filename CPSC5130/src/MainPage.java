import javax.swing.JFrame;

public class MainPage {
	
	public static void main(String[] args) {
		
		ChoiceGui choiceGui = new ChoiceGui();
		choiceGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choiceGui.setSize(1010,100);
		choiceGui.setLocationRelativeTo(null);
		choiceGui.setVisible(true);
	}
}

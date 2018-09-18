// Joel Franco; Brooklyn College/CUNY; CISC. 3120 - ER6; 11/8/16
// This is the the main class for the Multi Base Calculator
import javax.swing.JFrame;
public class MultiCalculator 
{
	public static void main(String[] args)
	{
		createAndShowGUI();
	}

	private static void createAndShowGUI() 
	{
		JFrame frame = new JFrame("Multi Base Calculator");

		frame.add(new MultiCalcPanel());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 750);
		frame.setVisible(true);
	}
}

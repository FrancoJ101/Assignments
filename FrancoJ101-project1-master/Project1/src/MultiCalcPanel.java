import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/** This class is the GUI for the Multi Base Calculator
 * 
 * @author Joel Franco; Brooklyn College/CUNY; CISC. 3120 - ER6; 11/8/16
 *
 */
public class MultiCalcPanel extends JPanel implements ActionListener, ChangeListener
{
	private JButton zero, one, two, three, four, five, six, seven, eight, nine;
	private JButton A, B, C, D, E, F;
	private JButton[] numButtonArray;
	private JButton plus, minus, multiply, divide, clear, equals;
	private JTextField number = new JTextField(""); 
	private JTextField result = new JTextField("");
	private JTextField baseDisplay = new JTextField("");
	private JTextArea historyDisplay = new JTextArea("");
	private JLabel historyLabel = new JLabel("Calculation History");
	private JPanel numberPanel = new JPanel(new GridLayout(6,3));
	private JPanel operationPanel = new JPanel(new GridLayout(6,1));
	private JPanel displayPanel = new JPanel(new GridLayout(2,2));
	private JPanel historyPanel = new JPanel(new GridLayout(2,1));
	private JSlider baseSelector = new JSlider(JSlider.HORIZONTAL, 2, 16, 10);
	private boolean addit = false;
	private boolean sub = false;
	private boolean multi = false;
	private boolean divid = false;
	private boolean activeOperation = false;
	private int base;
	private String inputString1;
	private String inputString2;
	private String historyString = "";

	/** The constructor initializes  and sets up the parts of the GUI.
	 * 
	 */
	MultiCalcPanel() 
	{
		this.setLayout(new BorderLayout() ); 
		zero = new JButton("0"); 
		one = new JButton("1");
		two = new JButton("2"); 
		three = new JButton("3"); 
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		A = new JButton("A");
		B = new JButton("B");
		C = new JButton("C");
		D = new JButton("D");
		E = new JButton("E");
		F = new JButton("F");
		plus = new JButton("+"); 
		minus = new JButton("-"); 
		multiply = new JButton("x"); 
		divide = new JButton("/"); 
		clear = new JButton("Clear");
		equals = new JButton("=");
       
		numberPanel.add(zero);
		numberPanel.add(one);
		numberPanel.add(two);
		numberPanel.add(three);
		numberPanel.add(four);
		numberPanel.add(five);
		numberPanel.add(six);
		numberPanel.add(seven);
		numberPanel.add(eight);
		numberPanel.add(nine);
		numberPanel.add(A);
		numberPanel.add(B);
		numberPanel.add(C);
		numberPanel.add(D);
		numberPanel.add(E);
		numberPanel.add(F);
		add(numberPanel, BorderLayout.CENTER);
		
		operationPanel.add(plus);
		operationPanel.add(minus);
		operationPanel.add(multiply);
		operationPanel.add(divide);
		operationPanel.add(equals);
		operationPanel.add(clear);
		add(operationPanel, BorderLayout.EAST);
		
		baseSelector.addChangeListener(this);
		baseSelector.setMajorTickSpacing(1);
		baseSelector.setPaintTicks(true);
		baseSelector.setPaintLabels(true);
		
		number.setEditable(false);
		result.setEditable(false);
		baseDisplay.setEditable(false);
		displayPanel.add(number);
		displayPanel.add(result);
		displayPanel.add(baseDisplay);
		displayPanel.add(baseSelector);
		add(displayPanel,BorderLayout.NORTH);
		
		historyPanel.add(historyLabel);
		historyDisplay.setEditable(false);
		historyPanel.add(historyDisplay);
		add(historyPanel,BorderLayout.SOUTH);
		
		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		A.addActionListener(this);
		B.addActionListener(this);
		C.addActionListener(this);
		D.addActionListener(this);
		E.addActionListener(this);
		F.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		multiply.addActionListener(this);
		divide.addActionListener(this);
		clear.addActionListener(this);
		equals.addActionListener(this);
		
		numButtonArray = new JButton[]{zero,one,two,three,four,five,six,seven,eight,nine,A,B,C,D,E,F};
		baseSelector.setValue(4);
	}
	/** This method gets the buttons selected by the user and performs actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0;i < numButtonArray.length;i++)
		{
			if (e.getSource() == numButtonArray[i])
			{
				number.setText(number.getText() + numButtonArray[i].getText() );
			}		
		}
		if (e.getActionCommand().equals("+"))
		{
			setInput1();
			addit = true;
		}
		else if (e.getActionCommand().equals("-"))
		{
			setInput1();
			sub = true;
		}
		else if (e.getActionCommand().equals("x"))
		{
			setInput1();
			multi = true;
		}
		else if (e.getActionCommand().equals("/"))
		{
			setInput1();
			divid = true;
		}
		else if (e.getActionCommand().equals("Clear"))
		{
			addit = false;
			sub = false;
			multi = false;
			divid = false;
			activeOperation = false;
			number.setText("");
			result.setText("");
		}
		else if (e.getActionCommand().equals("="))
		{
			getResults();
		}
	}
	/** This method is for the first input entered by the user.
	 *  Avoids some duplicate code.
	 */
	public void setInput1()
	{
		if (activeOperation == true)
		{	
			getResults();
			number.setText(result.getText() );
		}
		inputString1 = number.getText();
		historyString = historyString + number.getText();
     	number.setText("");
     	activeOperation = true;
	}
	/** This method gets the results of the operations entered by the user.
	 *  It also creates the history string for calculation history. 
	 */
	public void getResults()
	{
		inputString2 = number.getText();
		MultiCalc calc = new MultiCalc(inputString1, inputString2, base); 
		if(addit)
		{
			result.setText(calc.giveAddCalc());
			historyString = historyString + " + " + number.getText() + " = " + result.getText() + "\t Base: " + base + "\n";
			historyDisplay.setText(historyString);
			addit = false;
			number.setText("");
		}
		else if(sub)
		{
			result.setText(calc.giveSubCalc());
			historyString = historyString + " - " + number.getText() + " = " + result.getText() + "\t Base: " + base + "\n";
			historyDisplay.setText(historyString);
			sub = false;
			number.setText("");
		}
		else if(multi)
		{
			result.setText(calc.giveMultiCalc());
			historyString = historyString + " * " + number.getText() + " = " + result.getText() + "\t Base: " + base + "\n";
			historyDisplay.setText(historyString);
			multi = false;
			number.setText("");
		}
		else if(divid)
		{
			result.setText(calc.giveDivCalc());
			historyString = historyString + " / " + number.getText() + " = " + result.getText() + "\t Base: " + base + "\n";
			historyDisplay.setText(historyString);
			divid = false;
			number.setText("");
		}
	}
	/** This method gets the selection on the JSlider and acts.
	 */
	@Override
	public void stateChanged(ChangeEvent e) 
	{
	    JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) 
	    {
	        int selection = (int)source.getValue();
	        for(int i = baseSelector.getMinimum(); i < baseSelector.getMaximum()+1;i++)
	        {
	        	if(selection == i)
	        	{
	        		base = i;
	        		baseDisplay.setText("Base " + i + " Selected");
	        		controlButtons(i);
	        	}		
	        }
	    }
	}
	/** This method makes the buttons visible/invisible based on i.
	 * 
	 * @param i The current selection on the JSlider.
	 */
	public void controlButtons(int i)
	{
		for(int index = i; index < numButtonArray.length; index++)
    	{
    		numButtonArray[index].setEnabled(false);
    		numButtonArray[index].setVisible(false);
    	}
		for(int index = 0; index < base; index++)
    	{
    		numButtonArray[index].setEnabled(true);
    		numButtonArray[index].setVisible(true);
    	}
	}
}

/** This class is performs the calculations for the Multi Base Calculator
 * 
 * @author Joel Franco; Brooklyn College/CUNY; CISC. 3120 - ER6; 11/8/16
 *
 */
public class MultiCalc 
{
	private int input1;
	private int input2;
	private int base;
	
	/**
	 * This constructor accepts two Strings and an integer.
	 * The strings are numbers of base b and they are converted to base 10.
	 * 
	 * @param x Number that is changed to base 10. 
	 * @param y Number that is changed to base 10.
	 * @param b Base of the numbers in the strings.
	 */
	public MultiCalc(String x, String y, int b)
	{
		base = b;
		input1 = getB10(x, base);
		input2 = getB10(y, base);
	}
	
	/** This method performs the addition 
	 * 
	 * @return The String returned is the sum in base x.
	 */
	public String giveAddCalc()
	{
	    return getBaseX(input1 + input2, base);
	}
	/** This method performs the subtraction
	 * 
	 * @return The String returned is the number in base x.
	 */
	public String giveSubCalc()
	{
		return getBaseX(input1 - input2, base);
	}
	/** This method performs the multiplication
	 * 
	 * @return The String returned is the number in base x.
	 */
	public String giveMultiCalc()
	{
		return getBaseX(input1 * input2, base);
	}
	/** This method performs the division.
	 * 
	 * @return The String returned is the number in base x.
	 */
	public String giveDivCalc()
	{
		return getBaseX(input1 / input2, base);
	}
	
	/** This method takes a base 10 number (x) of base b and creates a String.
	 * 
	 * @param x This integer is in base 10.
	 * @param b This is the base the number will be converted to.
	 * @return A number String is returned. 
	 */
	private String getBaseX(int x, int b)
	{
		int div = x / b;
		int rem = x % b;
		String remString = "";
		int count = 0;
		
		if (div == 0 && rem < 0 ) // Can't calculate when rem is negative, so I change to positive to avoid exceptions.
		{ rem = rem * -1; }
		do
		{ 
			if (rem == 10)
			{ remString = 'A' + remString; }
			else if (rem == 11)
			{ remString = 'B' + remString; }
			else if (rem == 12)
			{ remString = 'C' + remString; }
			else if (rem == 13)
			{ remString = 'D' + remString; }
			else if (rem == 14)
			{ remString = 'E' + remString; }
			else if (rem == 15)
			{ remString = 'F' + remString; }
			else
			{ remString = rem + remString; }
			rem = div % b;
			div = div / b;
			if (div == 0) // To get the complete number, the loop has to run one more time after div reaches 0.
			{count++;}
		}
		while(count < 2);
		if (remString.charAt(0) == '0') // Sometimes an extra 'O' comes in before the string. 
		{ return remString.substring(1); }
		else
		{ return remString; }
	}
	
	/** This method takes a (String)number of base b and converts it to base 10.
	 * 
	 * @param y The number that is converted to base 10.
	 * @param b The base of the number in the String.
	 * @return An integer in base 10 is returned.
	 */
	private int getB10(String y, int b)
	{
		String change = y;
		int number = 0;
		int digit = 0;
		char x;
		int exp = 0;
	    
		for(int i = change.length() - 1; i >= 0; i--)
		{
			x = change.charAt(i);
			if (x == 'A')
			{ digit = 10; }
			else if(x == 'B')
			{ digit = 11; }
			else if(x == 'C')
			{ digit = 12; }
			else if(x == 'D')
			{ digit = 13; }
			else if(x == 'E')
			{ digit = 14; }
			else if(x == 'F')
			{ digit = 15; }
			else
			{ digit =  Character.getNumericValue(x);}
			number += digit * (int) (Math.pow(b, exp)) ; 
			exp++;
		}
		return number;
	}
}

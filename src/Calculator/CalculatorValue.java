package Calculator;

import java.util.Scanner;

import uNumberLibrary.UNumber;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author SHahariar Haque 
 * @author Abdul Kader
 * @version 4.00	2017-10-18 Long integer implementation of the CalculatorValue class 
 * @version 4.1     2019-01-20 Long integer implementation of the CalculatorValue class
 * @version 4.2     2019-02-17 Implementation of the Double Calculator in CalculatorValue class
 * @version 4.3     2019-03-19 Implementation of  FSM to provide more accurate error message
 * @Version 4.5     2019-10-22 Implementation of part-4 DoubleCalculatorErrorTerm with UNumber.
 * <p>  I will work on user interface. In order to create label and text field for error term.
	I will add a new button (±) in calculator between the operand of main value as well as error term operand.
	I will work with me to implement FSM on error term.
	Kader have worked on implementing FSM on error term.
   Kader have worked on the logic to perform all the operator with error terms.
	He have worked on calling other classes in UserInterface class in order to perform the error term message. I  have also worked on defining the error value by default 0.05.</p>
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	double measuredValue = 0;
	String errorMessage = "";
	double errorValue = 0;
	UNumber measuredValue21;
	UNumber two = new UNumber(2);
	UNumber mValue= new UNumber();
	UNumber n = new UNumber(0.5);
	
	static UNumber eValue= new UNumber();
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(double v, double e) {
		measuredValue = v;
		errorValue = e;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorValue = v.errorValue;
		errorMessage = v.errorMessage;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */
	public CalculatorValue(String s, String e) {
		measuredValue = 0.0;
		if (s.length() == 0.0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an Double value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
			errorMessage = "This character may only be an \"E\", an \"e\", a digit , " + "a \".\", or "
					+ "it must be the end of the input. \n "; 	
			
			
			
			// double value.  If not, signal there
			tempScanner.close();								// return a zero
			return;												
		}
		
		// Convert the user-entered string to a double value and see if something else is following it
		measuredValue = tempScanner.nextDouble();				// Convert the value and check to see
		if (tempScanner.hasNext()) {						// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();							// is an error.  Therefore we must
			measuredValue = 0;								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			measuredValue = -measuredValue;					// on the state of the flag that
	
		
		//Error Term 
		errorValue = 0.05;
		if (e.length() == 0.0) {								// If there is nothing there,
			errorMessage = "";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start1 = 0;										// Start at character position zero
								// Assume the value is not negative
		if (e.charAt(start1) == '+')							// See if the first character is '+'
			 start1++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (e.charAt(start1) == '-'){					// See if the first character is '-'
			start1++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an Double value
		Scanner tempScanner1 = new Scanner(e.substring(start));// Create scanner for the digits
		if (!tempScanner1.hasNextDouble()) {					// See if the next token is a valid
			errorMessage = "This character may only be an \"E\", an \"e\", a digit , " + "a \".\", or "
					+ "it must be the end of the input. \n "; 	
			
			
			
			// double value.  If not, signal there
			tempScanner1.close();								// return a zero
			return;												
		}
		
		// Convert the user-entered string to a double value and see if something else is following it
		errorValue = tempScanner1.nextDouble();				// Convert the value and check to see
		if (tempScanner1.hasNext()) {						// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner1.close();							// is an error.  Therefore we must
			errorValue = 0.05;								// return a zero value.
			return;													
		}
		tempScanner1.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			errorValue = -errorValue;					// on the state of the flag that
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(double v){
		measuredValue = v;
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	
	 

		/**********************************************************************************************
		 * 
		 * Result attributes to be used for GUI applications where the returned string error message
		 * and pointer to the character of the error are not adequate for the required output.
		 * 
		 */

		public static String measuredValueErrorMessage = "";	// The alternate error message text
		public static String measuredValueInput = "";		// The input being processed
		public static int measuredValueIndexofError = -1;		// The index where the error was located
		private static int state = 0;						// The current state value
		private static int nextState = 0;					// The next state value
		private static String inputLine = "";				// The input line
		private static char currentChar;						// The current character in the line
		private static int currentCharNdx;					// The index of the current character
		private static boolean running;						// The flag that specifies if it is running

		/**********
		 * This private method display the input line and then on a line under it displays an up arrow
		 * at the point where an error was detected.  This method is designed to be used to display the
		 * error message on the console terminal.
		 * 
		 * @param input				The input string
		 * @param currentCharNdx		The location where an error was found
		 * @return					Two lines, the entire input line followed by a line with an up arrow
		 */
		private static String displayInput(String input, int currentCharNdx) {
			// Display the entire input line
			String result = input + "\n";

			// Display a line with enough spaces so the up arrow point to the point of an error
			for (int ndx=0; ndx < currentCharNdx; ndx++) result += " ";

			// Add the up arrow to the end of the second line
			return result + "\u21EB";				// A Unicode up arrow with a base
		}


		/*private static void displayDebuggingInfo() {
			if (currentCharNdx >= inputLine.length())
				System.out.println(((state < 10) ? "  " : " ") + state + 
						((finalState) ? "       F   " : "           ") + "None");
			else
				System.out.println(((state < 10) ? "  " : " ") + state + 
						((finalState) ? "       F   " : "           ") + "  " + currentChar + " " + 
						((nextState < 10) && (nextState != -1) ? "    " : "   ") + nextState );
		}*/

		private static void moveToNextCharacter() {
			currentCharNdx++;
			if (currentCharNdx < inputLine.length())
				currentChar = inputLine.charAt(currentCharNdx);
			else {
				currentChar = ' ';
				running = false;
			}
		}

		/**********
		 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
		 * method.
		 * 
		 * @param input		The input string for the Finite State Machine
		 * @return			An output string that is empty if every things is okay or it will be
		 * 						a string with a help description of the error follow by two lines
		 * 						that shows the input line follow by a line with an up arrow at the
		 *						point where the error was found.
		 */
		public static String checkMeasureValue(String input) {
			if(input.length() <= 0) return "";
			// The following are the local variable used to perform the Finite State Machine simulation
			state = 0;							// This is the FSM state number
			inputLine = input;					// Save the reference to the input line as a global
			currentCharNdx = 0;					// The index of the current character
			currentChar = input.charAt(0);		// The current character from the above indexed position

			// The Finite State Machines continues until the end of the input is reached or at some 
			// state the current character does not match any valid transition to a next state

			measuredValueInput = input;			// Set up the alternate result copy of the input
			running = true;						// Start the loop
			//System.out.println("\nCurrent Final Input  Next\nState   State Char  State");

			// The Finite State Machines continues until the end of the input is reached or at some 
			// state the current character does not match any valid transition to a next state
			while (running) {
				// The switch statement takes the execution to the code for the current state, where
				// that code sees whether or not the current character is valid to transition to a
				// next state
				switch (state) {
				case 0: 
					// State 0 has three valid transitions.  Each is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 1
					if (currentChar >= '0' && currentChar <= '9') {
						nextState = 1;
						break;
					}
					// If the current character is a decimal point, it transitions to state 3
					else if (currentChar == '.') {
						nextState = 3;
						break;					
					}
					
					// If it is none of those characters, the FSM halts
					else 
						running = false;
					
					// The execution of this state is finished
					break;
				
				case 1: 
					// State 1 has three valid transitions.  Each is addressed by an if statement.
					
					// In state 1, if the character is 0 through 9, it is accepted and we stay in this
					// state
					if (currentChar >= '0' && currentChar <= '9') {
						nextState = 1;
						break;
					}
					
					// If the current character is a decimal point, it transitions to state 2
					else if (currentChar == '.') {
						nextState = 2;
						break;
					}
					// If the current character is an E or an e, it transitions to state 5
					else if (currentChar == 'E' || currentChar == 'e') {
						nextState = 5;
						break;
					}
					// If it is none of those characters, the FSM halts
					else
						running = false;
					
					// The execution of this state is finished
					break;			
					
				case 2: 
					// State 2 has two valid transitions.  Each is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 1
					if (currentChar >= '0' && currentChar <= '9') {
						nextState = 2;
						break;
					}
					// If the current character is an 'E' or 'e", it transitions to state 5
					else if (currentChar == 'E' || currentChar == 'e') {
						nextState = 5;
						break;
					}

					// If it is none of those characters, the FSM halts
					else 
						running = false;

					// The execution of this state is finished
					break;
		
				case 3:
					// State 3 has only one valid transition.  It is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 1
					if (currentChar >= '0' && currentChar <= '9') {
						nextState = 4;
						break;
					}

					// If it is none of those characters, the FSM halts
					else 
						running = false;

					// The execution of this state is finished
					break;

				case 4: 
					// State 4 has two valid transitions.  Each is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 4
					if (currentChar >= '0' && currentChar <= '9') {
						nextState = 4;
						break;
					}
					// If the current character is an 'E' or 'e", it transitions to state 5
					else if (currentChar == 'E' || currentChar == 'e') {
						nextState = 5;
						break;
					}

					// If it is none of those characters, the FSM halts
					else 
						running = false;

					// The execution of this state is finished
					break;

				case 5: 
					if(currentChar>='0' && currentChar<='9') {
						nextState=7;
						break;
					}
					else if(currentChar=='+'|| currentChar=='-') {
						nextState=6;
						break;
					}
					break;

				case 6: 
					if(currentChar>='0' && currentChar<='9') {
						nextState=7;
						break;
					}
					else
						running=false;
					break;


				case 7: 
					if(currentChar>='0' && currentChar<='9') {
						nextState=7;
						break;
					}
					else
						running=false;
					break;
				}
				
				if (running) {
					//displayDebuggingInfo();
					// When the processing of a state has finished, the FSM proceeds to the next character
					// in the input and if there is one, it fetches that character and updates the 
					// currentChar.  If there is no next character the currentChar is set to a blank.
					moveToNextCharacter();
					
					// Move to the next state
					state = nextState;

				}
				// Should the FSM get here, the loop starts again

			}

			//System.out.println("The loop has ended.");

			measuredValueIndexofError = currentCharNdx;		// Copy the index of the current character;
			
			// When the FSM halts, we must determine if the situation is an error or not.  That depends
			// of the current state of the FSM and whether or not the whole string has been consumed.
			// This switch directs the execution to separate code for each of the FSM states.
			switch (state) {
			case 0:
				// State 0 is not a final state, so we can return a very specific error message
				measuredValueIndexofError = currentCharNdx;		// Copy the index of the current character;
				measuredValueErrorMessage = "The first character must be a digit";
				return "The first character must be a digit or a decimal point.";

			case 1:
				// State 1 is a final state, so we must see if the whole string has been consumed.
				if (currentCharNdx<input.length()) {
					// If not all of the string has been consumed, we point to the current character
					// in the input line and specify what that character must be in order to move
					// forward.
					measuredValueErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "
							+ "a \".\", or it must be the end of the input.\n";
					return measuredValueErrorMessage + displayInput(input, currentCharNdx);
				}
				else {
					measuredValueIndexofError = -1;
					measuredValueErrorMessage = "";
					return measuredValueErrorMessage;
				}

			case 2:
			case 4:
				// States 2 and 4 are the same.  They are both final states with only one possible
				// transition forward, if the next character is an E or an e.
				if (currentCharNdx<input.length()) {
					measuredValueErrorMessage = "This character may only be an \"E\", an \"e\", or it must"
							+ " be the end of the input.\n";
					return measuredValueErrorMessage + displayInput(input, currentCharNdx);
				}
				// If there is no more input, the input was recognized.
				else {
					measuredValueIndexofError = -1;
					measuredValueErrorMessage = "";
					return measuredValueErrorMessage;
				}
			case 3:
			case 6:
				// States 3, and 6 are the same. None of them are final states and in order to
				// move forward, the next character must be a digit.
				measuredValueErrorMessage = "This character may only be a digit.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);

			case 7:
				// States 7 is similar to states 3 and 6, but it is a final state, so it must be
				// processed differently. If the next character is not a digit, the FSM stops with an
				// error.  We must see here if there are no more characters. If there are no more
				// characters, we accept the input, otherwise we return an error
				if (currentCharNdx<input.length()) {
					measuredValueErrorMessage = "This character may only be a digit.\n";
					return measuredValueErrorMessage + displayInput(input, currentCharNdx);
				}
				else {
					measuredValueIndexofError = -1;
					measuredValueErrorMessage = "";
					return measuredValueErrorMessage;
				}

			case 5:
				// State 5 is not a final state.  In order to move forward, the next character must be
				// a digit or a plus or a minus character.
				measuredValueErrorMessage = "This character may only be a digit, a plus, or minus "
						+ "character.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			default:
				return "";
			}
		}

		
		public String toString() {
			return mValue.toStringDecimal();
		}
		
		
		public static String toStringErrTerm() {
			return eValue.toStringDecimal();
			
		}
		
		public String debugToString() {
			return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
		}
		
		public String debugToStringErrTerm() {
			return "errorTerm = " + errorValue + "\n";
		}
		/**********
		 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
		 * method.
		 * 
		 * @param input		The input string for the Finite State Machine
		 * @return			An output string that is empty if every things is okay or it will be
		 * 						a string with a help description of the error follow by two lines
		 * 						that shows the input line follow by a line with an up arrow at the
		 *						point where the error was found.
		 */
		
		public static String errorTermErrorMessage = "";	// The alternate error message text
		public static String errorTermInput = "";		// The input being processed
		public static int errorTermIndexofError = -1;		// The index where the error was located
		private static int state1 = 0;						// The current state value
		private static int nextState1 = 0;					// The next state value
		private static String inputLine1 = "";				// The input line
		private static char currentChar1;						// The current character in the line
		private static int currentCharNdx1;					// The index of the current character
		private static boolean running1;						// The flag that specifies if it is running

		/**********
		 * This private method display the input line and then on a line under it displays an up arrow
		 * at the point where an error was detected.  This method is designed to be used to display the
		 * error message on the console terminal.
		 * 
		 * @param input				The input string
		 * @param currentCharNdx1		The location where an error was found
		 * @return					Two lines, the entire input line followed by a line with an up arrow
		 */
		private static String displayInput1(String input, int currentCharNdx1) {
			// Display the entire input line
			String result = input + "\n";

			// Display a line with enough spaces so the up arrow point to the point of an error
			for (int ndx=0; ndx < currentCharNdx1; ndx++) result += " ";

			// Add the up arrow to the end of the second line
			return result + "\u21EB";				// A Unicode up arrow with a base
		}


		/*private static void displayDebuggingInfo() {
			if (currentCharNdx1 >= inputLine1.length())
				System.out.println(((state1 < 10) ? "  " : " ") + state1 + 
						((finalState1) ? "       F   " : "           ") + "None");
			else
				System.out.println(((state < 10) ? "  " : " ") + state + 
						((finalState) ? "       F   " : "           ") + "  " + currentChar + " " + 
						((nextState < 10) && (nextState != -1) ? "    " : "   ") + nextState );
		}*/

		private static void moveToNextCharacter1() {
			currentCharNdx1++;
			if (currentCharNdx1 < inputLine1.length())
				currentChar1 = inputLine1.charAt(currentCharNdx1);
			else {
				currentChar1 = ' ';
				running1 = false;
			}
		}

		/**********
		 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
		 * method.
		 * 
		 * @param input		The input string for the Finite State Machine
		 * @return			An output string that is empty if every things is okay or it will be
		 * 						a string with a help description of the error follow by two lines
		 * 						that shows the input line follow by a line with an up arrow at the
		 *						point where the error was found.
		 */
		public static String checkErrorTerm(String input) {
			if(input.length() <= 0) return "";
			// The following are the local variable used to perform the Finite State Machine simulation
			state1 = 0;							// This is the FSM state number
			inputLine1 = input;					// Save the reference to the input line as a global
			currentCharNdx1 = 0;					// The index of the current character
			currentChar1 = input.charAt(0);		// The current character from the above indexed position

			// The Finite State Machines continues until the end of the input is reached or at some 
			// state the current character does not match any valid transition to a next state

			errorTermInput = input;			// Set up the alternate result copy of the input
			running1 = true;						// Start the loop
			//System.out.println("\nCurrent Final Input  Next\nState   State Char  State");

			// The Finite State Machines continues until the end of the input is reached or at some 
			// state the current character does not match any valid transition to a next state
			while (running1) {
				// The switch statement takes the execution to the code for the current state, where
				// that code sees whether or not the current character is valid to transition to a
				// next state
				switch (state1) {
				case 0: 
					// State 0 has three valid transitions.  Each is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 1
					if (currentChar1 >= '1' && currentChar1 <= '9') {
						nextState1 = 1;
						break;
					}
					// If the current character is a decimal point, it transitions to state 3
					else if (currentChar1 == '.') {
						nextState1= 3;
						break;					
					}
					else if(currentChar1 =='0') {
						nextState1=8;
					}
					
					// If it is none of those characters, the FSM halts
					else 
						running1 = false;
					
					// The execution of this state is finished
					break;
				
				case 1: 
					// State 1 has three valid transitions.  Each is addressed by an if statement.
					
					// In state 1, if the character is 0 through 9, it is accepted and we stay in this
					// state
					if (currentChar1 >= '0' && currentChar1 <= '9') {
						nextState1 = 1;
						break;
					}
					
					// If the current character is a decimal point, it transitions to state 2
					else if (currentChar1 == '.') {
						nextState1 = 2;
						break;
					}
					// If the current character is an E or an e, it transitions to state 5
					else if (currentChar1 == 'E' || currentChar1 == 'e') {
						nextState1 = 5;
						break;
					}
					// If it is none of those characters, the FSM halts
					else
						running1 = false;
					
					// The execution of this state is finished
					break;			
					
				case 2: 
					// State 2 has two valid transitions.  Each is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 1
					if (currentChar1 >= '0' && currentChar1 <= '9') {
						nextState1 = 2;
						break;
					}
					// If the current character is an 'E' or 'e", it transitions to state 5
					else if (currentChar1 == 'E' || currentChar1 == 'e') {
						nextState1 = 5;
						break;
					}

					// If it is none of those characters, the FSM halts
					else 
						running1 = false;

					// The execution of this state is finished
					break;
		
				case 3:
					// State 3 has only one valid transition.  It is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 1
					if (currentChar1 >= '0' && currentChar1 <= '9') {
						nextState1 = 4;
						break;
					}

					// If it is none of those characters, the FSM halts
					else 
						running1 = false;

					// The execution of this state is finished
					break;

				case 4: 
					// State 4 has two valid transitions.  Each is addressed by an if statement.
					
					// If the current character is in the range from 1 to 9, it transitions to state 4
					if (currentChar1 >= '0' && currentChar1 <= '9') {
						nextState1 = 4;
						break;
					}
					// If the current character is an 'E' or 'e", it transitions to state 5
					else if (currentChar1 == 'E' || currentChar1 == 'e') {
						nextState1 = 5;
						break;
					}

					// If it is none of those characters, the FSM halts
					else 
						running1 = false;

					// The execution of this state is finished
					break;

				case 5: 
					if(currentChar1>='0' && currentChar1<='9') {
						nextState1=7;
						break;
					}
					else if(currentChar1=='+'|| currentChar1=='-') {
						nextState1=6;
						break;
					}
					break;

				case 6: 
					if(currentChar1>='0' && currentChar1<='9') {
						nextState1=7;
						break;
					}
					else
						running1=false;
					break;


				case 7: 
					if(currentChar1>='0' && currentChar1<='9') {
						nextState1=7;
						break;
					}
					else
						running1=false;
					break;
				
				case 8:
					if (currentChar1=='.') {
					nextState1=9;
					break;
				}
				else
					running1= false;
				break;
				
				case 9:
					if(currentChar1=='0') {
						nextState1=9;
						break;
					}
					else if (currentChar1>='1' && currentChar1<='9') {
						nextState1 = 4;
						break;
					}
					else
						running1 = false;
						break;
				}
				
				if (running1) {
					//displayDebuggingInfo();
					// When the processing of a state has finished, the FSM proceeds to the next character
					// in the input and if there is one, it fetches that character and updates the 
					// currentChar.  If there is no next character the currentChar is set to a blank.
					moveToNextCharacter1();
					
					// Move to the next state
					state1 = nextState1;

				}
				// Should the FSM get here, the loop starts again

			}

			//System.out.println("The loop has ended.");

			errorTermIndexofError = currentCharNdx1;		// Copy the index of the current character;
			
			// When the FSM halts, we must determine if the situation is an error or not.  That depends
			// of the current state of the FSM and whether or not the whole string has been consumed.
			// This switch directs the execution to separate code for each of the FSM states.
			switch (state1) {
			case 0:
				// State 0 is not a final state, so we can return a very specific error message
				errorTermIndexofError = currentCharNdx1;		// Copy the index of the current character;
				errorTermErrorMessage = "The first character must be a digit or a decimal point.";
				return "The first character must be a digit or a decimal point.";

			case 1:
				// State 1 is a final state, so we must see if the whole string has been consumed.
				if (currentCharNdx1<input.length()) {
					// If not all of the string has been consumed, we point to the current character
					// in the input line and specify what that character must be in order to move
					// forward.
					errorTermErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "
							+ "a \".\", or it must be the end of the input.\n";
					return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
				}
				else {
					errorTermIndexofError = -1;
					errorTermErrorMessage = "";
					return errorTermErrorMessage;
				}

			case 2:
			case 4:
				// States 2 and 4 are the same.  They are both final states with only one possible
				// transition forward, if the next character is an E or an e.
				if (currentCharNdx1<input.length()) {
					errorTermErrorMessage = "This character may only be an \"E\", an \"e\", or it must"
							+ " be the end of the input.\n";
					return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
				}
				// If there is no more input, the input was recognized.
				else {
					errorTermIndexofError = -1;
					errorTermErrorMessage = "";
					return errorTermErrorMessage;
				}
			case 3:
			case 6:
				// States 3, and 6 are the same. None of them are final states and in order to
				// move forward, the next character must be a digit.
				errorTermErrorMessage = "This character may only be a digit.\n";
				return errorTermErrorMessage + displayInput1(input, currentCharNdx1);

			case 7:
			case 8:
			case 9:
				// States 7 is similar to states 3 and 6, but it is a final state, so it must be
				// processed differently. If the next character is not a digit, the FSM stops with an
				// error.  We must see here if there are no more characters. If there are no more 
				// characters, we accept the input, otherwise we return an error
				if (currentCharNdx1<input.length()) {
					errorTermErrorMessage = "This character may only be a digit.\n";
					return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
				}
				else {
					errorTermIndexofError = -1;
					errorTermErrorMessage = "";
					return errorTermErrorMessage;
				}

			case 5:
				// State 5 is not a final state.  In order to move forward, the next character must be
				// a digit or a plus or a minus character.
				errorTermErrorMessage = "This character may only be a digit, a plus, or minus "
						+ "character.\n";
				return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
			default:
				return "";
			}
		}


	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */
	public void add(CalculatorValue v) {
		
		UNumber measuredValue1 = new UNumber(measuredValue);          // Saving the value of operand 1 into an object
		measuredValue1 = new UNumber(measuredValue1,25);
		
		UNumber measuredValue2 = new UNumber(v.measuredValue);       // Saving the value of operand 2 into an object
		measuredValue2 = new UNumber(measuredValue2,25);
		
		UNumber errorTerm1 = new UNumber(errorValue);                // Saving the value of operand 1 error term into an object
		errorTerm1 = new UNumber(errorTerm1,25);
		
		UNumber errorTerm2 = new UNumber(v.errorValue);              // Saving the value of operand 1 error term into an object
		errorTerm2 = new UNumber(errorTerm2,25);
			
	    UNumber temporary1 = new UNumber(measuredValue1);            // Saving the value of operand 1 into an object
		temporary1 = new UNumber(temporary1,25);
		
		UNumber temporary2 = new UNumber(measuredValue2);            // Saving the value of operand 1 into an object
		temporary2 = new UNumber(temporary2,25);
	
		// Finding the operand 1 range 
		measuredValue1.sub(errorTerm1);                              // Finding the lower bound of operand 1
		temporary1.add(errorTerm1);                                  // Finding the upper bound of operand 1
		
		// Finding the operand 2 range 
		measuredValue2.sub(errorTerm2);                             // Finding the lower bound of operand 2
		temporary2.add(errorTerm2);                                 // Finding the upper bound of operand 2
		
		// Finding the range
		measuredValue1.add(measuredValue2);                        // adding lower bounds
		temporary1.add(temporary2);                                // adding upper bounds
		
		UNumber temporary3 = new UNumber(measuredValue1);          // Saving the lower bound 
		temporary3 = new UNumber(temporary3,25);          
		
		UNumber temporary4 = new UNumber(temporary1);             // Saving the upper bound 
		temporary4 = new UNumber(temporary4,25);
		
		// Finding average of the range
		measuredValue1.add(temporary1);  measuredValue1.div(two);
		
		// Finding error term of the range
		temporary4.sub(temporary3);	temporary4.div(two);
		
		mValue = new UNumber(measuredValue1);                     // Saving the result 
		
		eValue = new UNumber(temporary4);                         // Saving the error term value
		
		errorMessage = "";
	}

	/*****
	 *The below is the subtraction which subtracts the value.
	 * 
	 * @param v
	 */
	public void sub(CalculatorValue v) {
                                                                  // That is the logic for round off in error term.
		UNumber measuredValue1 = new UNumber(measuredValue);      // Saving the value of operand 1 into an object
		measuredValue1 = new UNumber(measuredValue1,25);
		
		UNumber measuredValue2 = new UNumber(v.measuredValue);    // Saving the value of operand 2 into an object
		measuredValue2 = new UNumber(measuredValue2,25);
		
		UNumber errorTerm1 = new UNumber(errorValue);             // Saving the value of operand 1 error term into an object
		errorTerm1 = new UNumber(errorTerm1,25);
		
		UNumber errorTerm2 = new UNumber(v.errorValue);           // Saving the value of operand 1 error term into an object
		errorTerm2 = new UNumber(errorTerm2,25);
		
		UNumber temporary1 = new UNumber(measuredValue1);         // Saving the value of operand 1 into an object
		temporary1 = new UNumber(temporary1,25);
		
		UNumber temporary2 = new UNumber(measuredValue2);         // Saving the value of operand 1 into an object
		temporary2 = new UNumber(temporary2,25);
		
		UNumber temporary3 = new UNumber(measuredValue1);        // Saving the lower bound 
		temporary3 = new UNumber(temporary3,25);          
		
		UNumber temporary4 = new UNumber(temporary1);            // Saving the upper bound 
		temporary4 = new UNumber(temporary4,25); 
		
		// Finding the operand 1 range 
		measuredValue1.sub(errorTerm1);                          // Finding the lower bound of operand 1
		temporary1.add(errorTerm1);                              // Finding the upper bound of operand 1
		
		// Finding the operand 2 range 
		measuredValue2.sub(errorTerm2);                          // Finding the lower bound of operand 2
		temporary2.add(errorTerm2);                              // Finding the upper bound of operand 2
		
		// Finding the range
		measuredValue1.sub(measuredValue2);                      // subtracting lower bounds
		temporary1.sub(temporary2);                              // subtracting upper bounds
		
		// Finding average of the range
		measuredValue1.add(temporary1);  measuredValue1.div(two);
		
		// Finding error term of the range
		temporary4.sub(temporary3);	temporary4.div(two);
		
		mValue = new UNumber(measuredValue1);                    // Saving the result 
		
		eValue = new UNumber(temporary4);                        // Saving the error term value
		
		errorMessage = "";
				
		
		
	}
	/*****
	 *The below is the multiplication which multiplies  the value.
	 * 
	 * @param v
	 */

	public void mpy(CalculatorValue v) {

		UNumber measuredValue1 = new UNumber(measuredValue);            // Saving the value of operand 1 into an object
		measuredValue1 = new UNumber(measuredValue1,25);
		
		UNumber measuredValue2 = new UNumber(v.measuredValue);          // Saving the value of operand 2 into an object
		measuredValue2 = new UNumber(measuredValue2,25);
		
		// Finding the product of operand 1 and operand 2
		UNumber errorTerm1 = new UNumber(errorValue);                  // Saving the value of operand 1 error term into an object
		errorTerm1 = new UNumber(errorTerm1,25);
				
		UNumber errorTerm2 = new UNumber(v.errorValue);                // Saving the value of operand 2 error term into an object
		errorTerm2 = new UNumber(errorTerm2,25);
		
		UNumber temporary1 = new UNumber(measuredValue1);              // Saving the value of operand 1 into an object
		temporary1 = new UNumber(temporary1,25);
		
		UNumber temporary2 = new UNumber(measuredValue2);              // Saving the value of operand 1 into an object
		temporary2 = new UNumber(temporary2,25);
		
		temporary1.mpy(temporary2); 
		
		// Finding error term
		errorTerm1.div(measuredValue1);                               // Finding the error fraction of operand 1 
		
		errorTerm2.div(measuredValue2);                               // Finding the error fraction of operand 2 
		
		errorTerm1.add(errorTerm2);		                              // Adding the error fractions
		
		errorTerm1.mpy(temporary1);                                   // Multiplying the error fraction with the product 
			
		mValue = new UNumber(temporary1);                             // Saving the product 
		
		eValue = new UNumber(errorTerm1);                             // Saving the error term value
		
		errorMessage = "";
		
	}
	/*****
	 *The below is the division which divides the value.
	 * 
	 * @param v
	 */
	public void div(CalculatorValue v) {
		
		UNumber measuredValue1 = new UNumber(measuredValue);           // Saving the value of operand 1 into an object
		measuredValue1 = new UNumber(measuredValue1,25);
		
		UNumber measuredValue2 = new UNumber(v.measuredValue);        // Saving the value of operand 2 into an object
		measuredValue2 = new UNumber(measuredValue2,25);
		
		UNumber errorTerm1 = new UNumber(errorValue);                 // Saving the value of operand 1 error term into an object
		errorTerm1 = new UNumber(errorTerm1,25);
		
		UNumber errorTerm2 = new UNumber(v.errorValue);               // Saving the value of operand 1 error term into an object
		errorTerm2 = new UNumber(errorTerm2,25);
		

	
		UNumber temporary1 = new UNumber(measuredValue1);            // Saving the value of operand 1 into an object
		temporary1 = new UNumber(temporary1,25);
		
		UNumber temporary2 = new UNumber(measuredValue2);            // Saving the value of operand 1 into an object
		temporary2 = new UNumber(temporary2,25);
		
		temporary1.div(temporary2);                                  // Findig Quotient of operand 1 and operand 2
		
		// Finding error term
		errorTerm1.div(measuredValue1);                              // Finding the error fraction of operand 1 
		
		errorTerm2.div(measuredValue2);                              // Finding the error fraction of operand 2 
		
		errorTerm1.add(errorTerm2);		                             // Adding the error fractions
		
		errorTerm1.mpy(temporary1);                                  // Multiplting the error fraction with the product 
			
		mValue = new UNumber(temporary1);                            // Saving the product 
		
		eValue = new UNumber(errorTerm1);                            // Saving the error term value
		
		errorMessage = "";
	}
	
	/*****
	 *The below is the log which calculates logarithmic value with base2.
	 * 
	 * @param v
	 */
	
	public void log(CalculatorValue v) {
    //measuredValue = Math.log(measuredValue)/Math.log(2);
		
		
	}

	public void sqrt(CalculatorValue v) {
		
		
		UNumber half = new UNumber(0.5);

		UNumber measuredValue1 = new UNumber(measuredValue);      // Saving the value of operand 1 into an object
		measuredValue1 = new UNumber(measuredValue1,25);
		measuredValue1.sqrt();
		measuredValue21 = new UNumber(measuredValue1 );

        UNumber errorTerm1 = new UNumber(errorValue);  // Saving the value of operand 1 error term into an object
        errorTerm1 = new UNumber(errorTerm1,25);

		UNumber temporary1 = new UNumber(measuredValue1);    // Saving the value of operand 1 into an object
		temporary1 = new UNumber(temporary1,25);

		UNumber errorvalue = new UNumber(errorValue);
		errorvalue = new UNumber(errorvalue, 25);



		errorTerm1.mpy(half);
		errorTerm1.mpy(temporary1);     // Finding the error fraction of operand 1


		    // Multiplying the error fraction with the product


		mValue = new UNumber(measuredValue21);    // Saving the product
		eValue = new UNumber(errorTerm1);  
		                                       // Saving the error term value
		errorMessage = "";  
		
	}
}


package Calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.StringTokenizer;

import uNumberLibrary.UNumberWithSqrt;

import Calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author Shahariar haque 
 * @author Abdul Kader
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * @version 4.1  	2019-01-21 Implementation of a integer calculator
 * @version 4.2     2019-02-19 Implementation of double calculator
 * @version 4.3     2019-03-19 Implementation of FSM to provide more accurate error message	
 * @Version 4.4     2019-04-08 Implementation of Error Term for more accurate Calculaton.
 * @Version 4.5     2019-10-22 Implementation of part-4 DoubleCalculatorErrorTerm with UNumber.
 * <p>  I will work on user interface. In order to create label and text field for error term.
	I will add a new button (±) in calculator between the operand of main value as well as error term operand.
	I will work with me to implement FSM on error term.
	Kader have worked on implementing FSM on error term.
	Kader have worked on the logic to perform all the operator with error terms.
	He have worked on calling other classes in UserInterface class in order to perform the error term message. I  have also worked on defining the error value by default 0.05.</p>
				
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH/2;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("UNumber Calculator with Sqrt");
	private Label label_Operand1 = new Label("First operand");
	private Label label_Operand1ErrorTerm = new Label("Error Term");
	private Label label_PlusMinus = new Label("\u00B1");
	private TextField text_Operand1 = new TextField();
	private TextField text_Operand1ErrorTerm = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private Label label_Operand2ErrorTerm = new Label("Error Term");
	private Label label_PlusMinus1 = new Label("\u00B1");
	private TextField text_Operand2 = new TextField();
	private TextField text_Operand2ErrorTerm = new TextField();
	private Label label_Result = new Label("Result");
	private Label label_ResultErrorTerm = new Label("Error Term");
	private Label label_PlusMinus2 = new Label("\u00B1");
	private TextField text_Result = new TextField();
	private TextField text_ResultErrorTerm = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				
	private Button button_Div = new Button("\u00F7");
	//private Button button_log = new Button("Log\u2082");
	private Button button_Sqrt = new Button("\u221A");
	
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand1ErrorTerm = new Label("");
	private Label label_errOperand2ErrorTerm = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");	
	private TextFlow errMeasuredValue;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();
    private TextFlow errMeasuredValue1;
    private Text errMVPart3 = new Text();
    private Text errMVPart4 = new Text();
    private TextFlow errErrorTerm;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();
    private TextFlow errErrorTerm1;
    private Text errETPart3 = new Text();
    private Text errETPart4 = new Text();

	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 7;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		label_IntegerCalculator.setStyle("-fx-underline: true;");
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 45);
		
		// Label the first operand just above the title
		setupLabelUI(label_Operand1ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 480, 45);
		
		// Label the "+-" symbol in middle of text fields.
		setupLabelUI(label_PlusMinus, "Arial", 40, 50, Pos.BASELINE_LEFT, 437, 60);
		
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/2, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		text_Operand1.setStyle("-fx-text-fill:black;");
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand1ErrorTerm.requestFocus(); });
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
				// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 230, Pos.BASELINE_LEFT, (Calculator.WINDOW_WIDTH/2)+80, 70, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		text_Operand1ErrorTerm.setStyle("-fx-text-fill:black;");
				
		// Establish an error message for the first operand Error Term, left aligned
				label_errOperand1ErrorTerm.setTextFill(Color.RED);
				label_errOperand1ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
				setupLabelUI(label_errOperand1ErrorTerm, "Arial", 14, 
						Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 
						Calculator.WINDOW_WIDTH/2 - 150, 126);
				
		
		// Establish an error message for the first operand error term just above i
		label_errOperand1.setTextFill(Color.RED);
		label_errOperand1.setAlignment(Pos.BASELINE_RIGHT);
				setupLabelUI(label_errOperand1, "Arial", 14,  
								Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 126);
				
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 145);
		setupLabelUI(label_Operand2ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 480, 145);
		setupLabelUI(label_PlusMinus1, "Arial", 40, 50, Pos.BASELINE_LEFT, 437, 160);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/2, Pos.BASELINE_LEFT, 10, 170, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		text_Operand2.setStyle("-fx-text-fill:black;");
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish the Error Term textfield for the second operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2ErrorTerm, "Arial", 18, 230, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 80, 170,true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {setOperand2(); });
		
		// Establish an error message for the second operand error term just above it with, left aligned
		label_errOperand2ErrorTerm.setTextFill(Color.RED);
		label_errOperand2ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2ErrorTerm, "Arial", 14,  
				Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 - 150, 220);
		
		// Establish an error message for the second operand just above it with, left aligned
		label_errOperand2.setTextFill(Color.RED);
		label_errOperand2.setAlignment(Pos.BASELINE_RIGHT);
				setupLabelUI(label_errOperand2, "Arial", 14,  
						Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 220);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 240);
		
		// Label the result error term just above the result output field, left aligned
		setupLabelUI(label_ResultErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 480, 240);
		
		// Label the "+-" just above the result output field
		setupLabelUI(label_PlusMinus2, "Arial", 40, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 437, 250);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH/2, Pos.BASELINE_LEFT, 10, 260, false);
		text_Result.setStyle("-fx-text-fill:black;");
		
		// Establish the Error Term textfield for the result.  If anything changes, process
				// all fields to ensure that we are ready to perform as soon as possible.
				setupTextUI(text_ResultErrorTerm, "Arial", 18, 230, Pos.BASELINE_LEFT, 
						Calculator.WINDOW_WIDTH/2 + 80, 260, false);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 220);
		label_errResult.setTextFill(Color.RED);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 320);
		button_Add.setStyle("-fx-text-fill:black;");
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 320);
		button_Sub.setStyle("-fx-text-fill:black;");
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 320);
		button_Mpy.setStyle("-fx-text-fill:black;");
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 320);
		button_Div.setStyle("-fx-text-fill:black;");
		button_Div.setOnAction((event) -> { divOperands(); });
		
		// Establish the LOG "log2" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 320);
		button_Sqrt.setStyle("-fx-text-fill:black;");
		button_Sqrt.setOnAction((event) -> { sqrtOperands1(); });
		
		
//		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 6 * buttonSpace-BUTTON_OFFSET, 320);
//		button_Sqrt.setStyle("-fx-text-fill:black;");
//		button_Sqrt.setOnAction((event) -> { sqrtOperands(); });
		//Error Message for the Measured Value for operand 1 
		
				errMVPart1.setFill(Color.BLACK);
			    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errMVPart2.setFill(Color.RED);
			    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errMeasuredValue = new TextFlow(errMVPart1, errMVPart2);
				errMeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				errMeasuredValue.setLayoutX(22);  
				errMeasuredValue.setLayoutY(100);
				
				// Error Message for the Measured Value for operand 2
				errMVPart3.setFill(Color.BLACK);
			    errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errMVPart4.setFill(Color.RED);
			    errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errMeasuredValue1 = new TextFlow(errMVPart3, errMVPart4);
				errMeasuredValue1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				errMeasuredValue1.setLayoutX(22);  
				errMeasuredValue1.setLayoutY(199);
				
				
				// Error Message for the Error Term for operand 1
			    errETPart1.setFill(Color.BLACK);
			    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errETPart2.setFill(Color.RED);
			    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errErrorTerm = new TextFlow(errETPart1, errETPart2);
				// Establish an error message for the first operand just above it with, left aligned
				errErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				errErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH/2+90);  
				errErrorTerm.setLayoutY(100);
				
				// Error Message for the Error Term for operand 2
			    errETPart3.setFill(Color.BLACK);
			    errETPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errETPart4.setFill(Color.RED);
			    errETPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errErrorTerm1 = new TextFlow(errETPart3, errETPart4);
				// Establish an error message for the second operand just above it with, left aligned
				errErrorTerm1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				errErrorTerm1.setLayoutX(Calculator.WINDOW_WIDTH/2+90);  
				errErrorTerm1.setLayoutY(199);

				

				
				
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1, 
				label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, 
				button_Add, button_Sub, button_Mpy, button_Div, errMeasuredValue,
				errMeasuredValue1,text_Operand1ErrorTerm, label_Operand1ErrorTerm, label_PlusMinus,label_Operand2ErrorTerm,
				text_Operand2ErrorTerm,label_PlusMinus1, label_PlusMinus2,text_ResultErrorTerm,label_ResultErrorTerm, 
				errErrorTerm,label_errOperand1ErrorTerm,label_errOperand2ErrorTerm,errErrorTerm1,button_Sqrt);

	}
	

	


	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		errMVPart1.setText("");
		errMVPart2.setText("");
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		label_errOperand1ErrorTerm.setText("");
		errETPart1.setText("");
		errETPart2.setText("");
		performGo();
		if (perform.setOperand1(text_Operand1.getText(), text_Operand1ErrorTerm.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			performGo();									// the error message that was generated
	}
	
	
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		errMVPart3.setText("");
		errMVPart4.setText("");
		label_errOperand2ErrorTerm.setText("");
		errETPart3.setText("");
		errETPart4.setText("");								
		performGo1();
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText(), text_Operand2ErrorTerm.getText())) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			performGo1();
	}
	
	
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();	// Call the business logic add method
		String theAnswerET = perform.errorTerm(); 
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			StringTokenizer st = new StringTokenizer(theAnswer,":");
			if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05"); // That is the logic for showing 0.05 in the error term 1
			if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05"); // That is the logic for showing 0.05 in the error term 2
			text_Result.setText(theAnswer);  						// If okay, display it in the result field and
			text_ResultErrorTerm.setText(theAnswerET);
			label_Result.setText("Sum");                   			// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){		
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;	                                                // without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the Subtraction and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.subtraction(); 
		String theAnswerET = perform.errorTerm();                                        // Call the business logic sub method
		label_errResult.setText("");								// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			StringTokenizer st = new StringTokenizer(theAnswer,":");
			if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05"); // That is the logic for showing 0.05 in the error term 1
			                                                                                          
			if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05");  // That is the logic for showing 0.05 in the error term 2
			text_Result.setText(theAnswer);  						// If okay, display it in the result field and
			text_ResultErrorTerm.setText(theAnswerET);						// If okay, display it in the result field and
			label_Result.setText("Difference");						// change the title of the field to "Difference"
		}
		else {														 // Some error occurred while doing the subtraction.
			text_Result.setText("");                                 // Do not display a result if there is an error.
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the Multiplication and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.multiplication();	
		String theAnswerET = perform.errorTerm();  // Call the business logic mpy method
		label_errResult.setText("");								// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			StringTokenizer st = new StringTokenizer(theAnswer,":");
			if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05"); // That is the logic for showing 0.05 in the error term 1
			if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05"); // That is the logic for showing 0.05 in the error term 2
			text_Result.setText(theAnswer);  						// If okay, display it in the result field and
			text_ResultErrorTerm.setText(theAnswerET);						// If okay, display it in the result field and
			label_Result.setText("Product");								// change the title of the field to "Product"
		}
		else {														// Some error occurred while doing the multiplication.
			text_Result.setText("");								// Do not display a result if there is an error.					
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the Division and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.division();
		String theAnswerET = perform.errorTerm();                                      // Call the business logic div method
		label_errResult.setText("");								// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			StringTokenizer st = new StringTokenizer(theAnswer,":");
			if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05");
			if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05");
			text_Result.setText(theAnswer);  						// If okay, display it in the result field and
			text_ResultErrorTerm.setText(theAnswerET);						// If okay, display it in the result field and
			label_Result.setText("Quotient");						// change the title of the field to "Quotient"
		 
		  if(BusinessLogic.zero) {
			  text_Result.setText("");
			  label_Result.setText("");
			  text_ResultErrorTerm.setText("");
			  label_errResult.setText("Divide By Zero Error");
			  }
		  }
	
		else{														// Some error occurred while doing the division.
			text_Result.setText("");								// Do not display a result if there is an error.					
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}
	
	//private void logOperands(){
		// Check to see if both operands are defined and valid
		//if (binaryOperandIssues()) 									// If there are issues with the operands, return
			//return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the logarithmic and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.logarithmic();
		String theAnswerET = perform.errorTerm();                                           // Call the business logic logarithmic method
		//label_errResult.setText("");								// Reset any result error messages from before
		//if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			//StringTokenizer st = new StringTokenizer(theAnswer,":");
			//text_Result.setText(theAnswer);  						// If okay, display it in the result field and
			//text_ResultErrorTerm.setText(theAnswerET);							// If okay, display it in the result field and
			//label_Result.setText("Log with base 2");	                        // change the title of the field to "Log with base 2"

			//if (BusinessLogic.zero) {
				//text_Result.setText("");                          //set the result text to empty when the value of operand1 is -ve.
				//label_Result.setText("");
				 //text_ResultErrorTerm.setText("");
				//label_errOperand1.setText("Value can't be negative for Log");  // change the title of the field to "Value can't be negative for Log"
			//}
		
		//}
		//else {														// Some error occurred while doing the multiplication.
			//text_Result.setText("");								// Do not display a result if there is an error.					
			//label_Result.setText("Result");							// Reset the result label if there is an error.
			//label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		//}
	//}	
	private void sqrtOperands1(){
	   if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;	
		
		

		// If the operands are defined and valid, request the business logic method to do the square root and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.squareRoot();		       			 // Call the business logic square root method				    			 // Reset any result error messages from before
		String theAnswerET = perform.errorTerm(); 

		// Reset any result error messages from before
		if (theAnswer.length() > 0) {								             // Check the returned String to see if it is okay
			
		

			text_Result.setText(theAnswer);			 // If okay, display it in the result field and
			text_ResultErrorTerm.setText(theAnswerET);


			label_Result.setText("Square root");	    				 // change the title of the field to "Square root"
		}
		else {	                                                         // Some error occurred while doing the square root.
			text_Result.setText(" ");			   			             // Do not display a result if there is an error.				
			label_Result.setText("Result");						         // Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());    // Display the error message.
		}								
	}

	private void performGo() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {
			//System.out.println(errMessage);
			label_errOperand1.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errMVPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}
		else {
			
			errMessage = CalculatorValue.checkErrorTerm(text_Operand1ErrorTerm.getText());
			if (errMessage != "") {
				//System.out.println(errMessage);
				label_errOperand1ErrorTerm.setText(CalculatorValue.errorTermErrorMessage);
				String input = CalculatorValue.errorTermInput;
				if (CalculatorValue.errorTermIndexofError <= -1) return;
				errETPart1.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
				errETPart2.setText("\u21EB");
			}
			
		}
		}
	
	private void performGo1() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
			//System.out.println(errMessage);
			label_errOperand2.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errMVPart3.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errMVPart4.setText("\u21EB");
		}
		else {
			
			errMessage = CalculatorValue.checkErrorTerm(text_Operand2ErrorTerm.getText());
			if (errMessage != "") {
				//System.out.println(errMessage);
				label_errOperand2ErrorTerm.setText(CalculatorValue.errorTermErrorMessage);
				if (CalculatorValue.errorTermIndexofError <= -1) return;
				String input = CalculatorValue.errorTermInput;
				errETPart3.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
				errETPart4.setText("\u21EB");
			}
			
		}
		}
}


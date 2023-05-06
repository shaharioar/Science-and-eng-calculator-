package uNumberLibrary;

public class UNumberWithSqrt extends UNumber {
int i;
	public UNumberWithSqrt() {
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(int v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(long v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(String str, int dec, boolean sign) {
		super(str, dec, sign);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(String str, int dec, boolean sign, int size) {
		super(str, dec, sign, size);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(UNumber that) {
		super(that);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(UNumber that, int size) {
		super(that, size);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(UNumber that, UNumber another) {
		super(that, another);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithSqrt(double v) {
		super(v);
		// TODO Auto-generated constructor stub
		
	}
	public void sqrt( ) {
		UNumberWithSqrt temp, temp2;
		temp = new UNumberWithSqrt (this);            
		temp2 = new UNumberWithSqrt (2);
		UNumberWithSqrt two = new UNumberWithSqrt (2);
		
		for(i=0;i<=25;i++) {                                           // The Number of digit has been indicated hare as loop
			 {
			}
			temp.div(temp2);                                     // The UNnumber of divition funtion has been called here.
			temp2.add(temp);                                    // The UNnumber of addition funtion has been called here.
			temp2.div(two);                                     // The UNnumber of divition funtion has been called here.
			temp = new UNumberWithSqrt (this) ;
			
			System.out.println("The value of iteration " + i + " = " + temp2.toDecimalString()); // Here the value has been print which will come from the Iteration.
		}

    	System.out.println("Square root Method Applied with the Half Method Rules");
    	System.out.println ("Author Name: Neaz Morshed");
    }
    
}

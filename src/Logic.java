import java.util.Vector;

public class Logic {
	double num;
	Vector<String> steps;
	DecimalConverter decimal;
	FractionalConverter frac;
	
	public Logic(double num) {
		this.num = num;
		steps = new Vector<String>();
		decimal = new DecimalConverter( (int) Math.floor(num));
		frac = new FractionalConverter( num );
	}
	
	void generate() {
		frac.generate();
		frac.testPrint();
		//decimal.generate();
		//decimal.testPrint();
	}
	
	/*
	 *  Convert the decimal part to binary
	 * */
	void decimalPart() {
		
	}
}

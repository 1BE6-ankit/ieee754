import java.util.Vector;


public class Logic {
	double num;
	Vector<String> steps;
	DecimalConverter decimal;
	
	public Logic(double num) {
		this.num = num;
		steps = new Vector<String>();
		decimal = new DecimalConverter( (int) Math.round(num));
	}
	
	void generate() {
		System.out.println("Inside Logic");
		decimal.generate();
		decimal.testPrint();
	}
	
	/*
	 *  Convert the decimal part to binary
	 * */
	void decimalPart() {
		
	}
}


class FractionalConverter{

	double num;
	
	FractionalConverter(){
				
	}
	
}
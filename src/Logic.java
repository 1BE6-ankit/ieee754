import java.util.ArrayList;
import java.util.HashMap;
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
//		frac.generate();
//		frac.testPrint();
		decimal.generate();
		//decimal.testPrint();
	}
	
	/*
	 * get all the fields required for decimalConverter
	 * */
	
	HashMap<Integer, Integer> dgetColumn1() {
		return decimal.getColumn1();
	}
	
	HashMap<Integer, Integer> dgetColumn2() {
		return decimal.getColumn2();
	}
	
	HashMap<Integer, ArrayList<Integer>> dgetColumn4() {
		// column 3 is obtained using column4
		return decimal.getColumn4();
	}
	
	int dgetTableSize() {
		return decimal.getTableSize();
	}
}

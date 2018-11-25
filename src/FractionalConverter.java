import java.util.ArrayList;
import java.util.HashMap;

class FractionalConverter{
	double num; // initial number that is fed for converting
	int steps;
	
	/* 
	 * this stores the values for every step
	 * 
	 * Mantissa working:
	 * ----------------------------------------
	 * decpart   |   decpart * 2    |   bitPart
	 * ----------------------------------------
	 * 0.15      |    0.30          |    {0}
	 * 0.30      |    0.60          |    {0, 0}
	 * ....
	 * ....
	 * ...
	 * #### The process finishes when 2nd column has value exactly 1, 
	 * or when the computer runs out of its capacity
	 * 
	 * */
	HashMap<Integer, Double> decPart;
	HashMap<Integer, ArrayList<Integer>> bitPart;
	
	FractionalConverter(double num){
		// initialize class members
		this.num = getFractionalPart(num);
		decPart = new HashMap<>();
		bitPart = new HashMap<>();
	}
	
	double getFractionalPart(double num) { // Get the fractional part of a double: 
											// eg: for 12.15, covert to string, and extract
											// value after the decimal
		String numString = String.valueOf(num);
		return Double.parseDouble( numString.substring(numString.indexOf(".")) );
	}
	
	void generate() {
		steps = recursiveGenerator(num, 1);
	}
	
	int recursiveGenerator(double n, int i) {
		/*
		 *  Main brain of this class
		 * */
		
		return 0;
	}
	
}
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
	 * 0.6       |    1.20          |    {0, 0, 1}
	 * 0.2       |    0.4 ....................   
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
		
		// to hold the current bit generated for this row
		int tempBit;
		
		// temporary arraylist to hold the previous bitPart list
		ArrayList<Integer> tempBitPart;
		if(i==1) {
			// for i==1, no bitPart exists, so create one immediately
			tempBitPart = new ArrayList<>();
		}else {
			tempBitPart = (ArrayList<Integer>) bitPart.get(i-1).clone();
		}
		
		// We only need the fractional Part
		if(n>1) n = getFractionalPart(n);
		
		// add the deimalPart representing first column
		decPart.put(i, n);
		
		// We take the unit place value only after the number * 2
		n = n * 2;
		
		// for the last column of bitArray
		tempBit = (n>=1)? 1 : 0;
		
		// push this value to the temporary array then push it to main array
		tempBitPart.add(tempBit);
		bitPart.put(i, tempBitPart);

		if(i==23 || n==1) return i;
		else return recursiveGenerator(n, i+1);
		
	}
	
	void testPrint() {
		for(int i=1; i<=steps; i++) {
			System.out.print("\n" + decPart.get(i));
			System.out.print(" - ");
			System.out.print(decPart.get(i)*2);
			System.out.print(" - ");
			System.out.println(bitPart.get(i));
		}
	}
	
}
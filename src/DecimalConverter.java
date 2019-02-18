
import java.util.ArrayList;
import java.util.HashMap;

class DecimalConverter{
	
	/* Number  |  Quotient   | Remainder  |  Bit set
	 * ---------------------------------------------
	 *  90     |     45      |    0       |   {0}
	 *  45     |     22      |    1       |   {1, 0} 
	 *  ....
	 * */
	
	int num;
	int steps;
	
	HashMap<Integer, Integer> divident;
	HashMap<Integer, Integer> quotient;
	HashMap<Integer, ArrayList<Integer>> remainderSet;
	
	DecimalConverter(int num) {
		this.num = num;
		divident = new HashMap<Integer, Integer>();
		quotient = new HashMap<Integer, Integer>();
		remainderSet = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	void generate() {
		steps = recursiveGenerator(num, 1);
	}
	
	int recursiveGenerator(int n, int i) {

		int quotient, remainder;
		ArrayList<Integer> tempRemainder;
		
		quotient = n / 2;
		remainder = n % 2;
		

		divident.put(i, n);
		this.quotient.put(i, quotient);
		
		if(i==1) tempRemainder = new ArrayList<Integer>();
		else tempRemainder = (ArrayList<Integer>) remainderSet.get(i-1).clone();
		
		tempRemainder.add(0, remainder);;
		remainderSet.put(i, tempRemainder);
		
		if(quotient==0) {return i;}
		else { return recursiveGenerator(quotient, i+1); }
	}
	
	void testPrint() {
		for(int i=1; i<=steps; i++) {
			System.out.print("\n" + divident.get(i));
			System.out.print(" - ");
			System.out.print(quotient.get(i));
			System.out.print(" - ");
			System.out.println(remainderSet.get(i));
		}
	}
	
	HashMap<Integer, Integer> getColumn1() {
		return divident;
	}
	
	HashMap<Integer, Integer> getColumn2() {
		return quotient;
	}
	
	HashMap<Integer, ArrayList<Integer>> getColumn4() {
		// column 3 is obtained using column4
		return remainderSet;
	}
	
	int getTableSize() {
		return steps;
	}
	
	
}

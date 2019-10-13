package sort;

/**
 * Insertion sort
 * @author Behdad Khamneli, lab2
 * @version 1
 *
 */
public class Insertion {
	/**
	 * regular insertion sort
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortInsert( Product[] x ) {
		for(int i = 1; i < x.length; i++) {
			for(int j = i; j > 0 && salesLess(x[j], x[j-1]); j--) {
				exch(x, j, j-1);// exchange if j-1 is bigger than j
			}
		}
	}
	/**
	 * checks which product is less
	 * first checks if sales of product v is less than product w, if the sales are equal
	 * compares the product ids and returns true if id of product v is less
	 * @param v - the product that needs to be compared
	 * @param w - the product that needs to be compared
	 * @return true if product v is less than product w, false otherwise
	 */
	private static boolean salesLess(Product v, Product w) {
		if (v.salesAmount() < w.salesAmount()) {
			return true;
		// if sales are equal compare their id
		}else if(v.salesAmount() == w.salesAmount()) {
			for (int i = 0; i < v.prodId().length(); i++) {
				if((int)v.prodId().charAt(i) != (int)w.prodId().charAt(i)) {
					if(((int)v.prodId().charAt(i) - (int)w.prodId().charAt(i)) < 0){
						return true;//true if the ASCII value of id of product v is less
					}else {
						return false;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * insertion sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortComparable ( Comparable[] x, int n ) {
		for(int i = 1; i < n; i++) {
			for (int j = i; j > 0 && less(x[j], x[j-1]); j--) {
					exch(x, j, j-1);
			}
		}
	}
	
	/**
	 * Check if v is less than w 
	 * @param v - the product that needs to be compared
	 * @param w - the other product that needs to be compared
	 * @return true if v should be located before w, false otherwise
	 * @see Product#compareTo(Product)
	 */
	private static boolean less(Comparable v, Comparable w) 
	{	return v.compareTo(w) < 0;	}
	
	/**
	 * Exchanges two elements of the array:comparable
	 * x[i] is swapped with x[j]
	 * @param x - the input array that the elements need to be swapped 
	 * @param i - index of the element that needs to be swapped with x[j]
	 * @param j - index of the element that needs to be swapped with x[i]
	 */
	private static void exch(Comparable[] x, int i, int j)
	{	Comparable t = x[i]; x[i] = x[j]; x[j] = t;	}
	
	
	/**
	 * optimized insertion sort
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortBinary ( Comparable[] x, int n ) {
		for (int i = 1; i < n; i++) {
			Comparable temp = x[i]; //holds the current first value
			int lo = 0;
			int hi = i;
			while(lo < hi) {
				int mid = lo + (hi - lo)/2; // the middle of the array
				if(less(temp, x[mid])) {
					hi = mid;
				}else
					lo = mid + 1;//look for the smallest in the right side of the array
			}
			for (int j = i; j > lo; j--) {
				x[j] = x[j-1];
				
			}
			
				x[lo] = temp;
			
		}
		
	}
	
}




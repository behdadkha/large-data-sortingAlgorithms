package sort;

/**
 * Heap sort
 * @author Behdad khamneli, lab 2
 * @version 1
 *
 */

public class Heap {
	/**
	 * Heap sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortHeap ( Comparable[] x, int n ) {
		
		for(int k = n/2; k >= 1; k--) {
			sink(x, k, n);
		}
		while(n > 1) {
			exch(x, 0, n-1);//exchange x[0] with x[n-1]
			n--;
			sink(x, 1, n);
		}
	}
	
	/**
	 * Sink the element at index i down the tree
	 * @param x - the array containing the products
	 * @param i - index of element that needs to be sinked
	 * @param n - the size of the input array
	 */
	private static void sink( Comparable[] x, int i, int n) {
		while(2*i <= n) {
			int j = 2*i;// 2i because children of i in heap are at position 2*i
			if(j < n && less(x[j-1], x[j])) j++;//go to next element
			if(!less(x[i-1], x[j-1])) break;
			exch(x, i-1, j-1);
			i = j;
		}
	}
	
	/**
	 * Check if v is less than w 
	 * @param v - the product that needs to be compared
	 * @param w - the other product that needs to be compared
	 * @return true if v is less than w, false otherwise
	 * @see Product#compareTo(Product)
	 */
	private static boolean less(Comparable v, Comparable w) 
	{	return v.compareTo(w) < 0;	}
	
	/**
	 * Exchange two elements in the array:comparable
	 * x[i] is swapped with x[j]
	 * @param x - the input array that the elements need to be swapped 
	 * @param i - index of the element that needs to be swapped with x[j]
	 * @param j - index of the element that needs to be swapped with x[i]
	 */
	private static void exch(Comparable[] x, int i, int j)
	{	Comparable t = x[i]; x[i] = x[j]; x[j] = t;	}
	
	
	
}

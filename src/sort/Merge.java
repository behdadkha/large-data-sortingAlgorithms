package sort;

/**
 * Merge sort
 * @author Behdad Khamneli, Lab2
 * @version 1
 *
 */
public class Merge {
	
	private static Comparable[] aux;// a temp array of Comparables 
	/**
	 * top-down merge sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeTD ( Comparable[] x, int n ) {
		aux = new Comparable[n];
		sort(x, 0, n - 1);
	}
	/**
	 * sort recursively 
	 * Divides the arrays into two sub-arrays and sorts each sub-array recursively
	 * @param x - the input array containing products that need to be sorted
	 * @param lo - index of the first element of the given array
	 * @param hi - index of the last element of the given array
	 */
	private static void sort(Comparable[] x, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(x, lo, mid);	// sort left half
		sort(x, mid+1, hi); // sort righ half
		merge(x, lo, mid, hi); // merge 2 sub-arrays 
	}
	/**
	 * merges the right and left sub-arrays
	 * uses a temp array to store the sorted array-->not in-place sort 
	 * @param x - the input array containing products that need to be sorted
	 * @param lo - index of the first element of the array
	 * @param mid - index of the middle of the array
	 * @param hi - index of the last element of the array
	 */
	private static void merge(Comparable[] x, int lo, int mid, int hi) {
		int i = lo, j= mid+1;
		for(int k = lo; k<= hi; k++) {
			aux[k] = x[k];//first fill up the aux with products
		}
		for(int k = lo; k <= hi; k++) {
			if (i > mid)		x[k] = aux[j++];//if i>mid, puts the aux[j] to x[k] and increment j
			else if (j > hi)	x[k] = aux[i++];
			else if (less(aux[j], aux[i])) x[k] = aux[j++];//puts the smallest product first
			else				x[k] = aux[i++];
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
	{return v.compareTo(w) < 0; }
	
	
	/**
	 * bottom-up merge sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeBU ( Comparable[] x, int n ) {
		aux = new Comparable[n];//new empty array
		for (int i = 1; i < n; i= i + i)// i is the sub-array size
			for (int lo = 0; lo < n-i; lo += i + i)// lo is sub-array index
				merge(x, lo, lo+i-1, Math.min(lo+i+i-1, n-1));
	}
	
}

package sort;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Quick sort
 * @author Behdad Khamneli, Lab2
 * @version 1
 *
 */
public class Quick {
	/**
	 * basic quick sort
	 * @param x - the input array containing products that need to be sorted.
	 * @see Quick#sort(Product[], int, int)
	 */
	public static void sortBasicQuick (  Product[] x ) {
		shuffle(x, x.length);// first shuffle to get the best performance
		sort(x, 0, x.length - 1);
	}
	
	/**
	 * helper function to sort
	 * converts the array into two partitions and sorts each partition
	 * @param x - input array containing the products that need to be sorted
	 * @param lo - index of the first element 
	 * @param hi - index of the last element
	 * @see Quick#partition(Product[], int, int)
	 */
	private static void sort(Product[] x, int lo, int hi) {
		if(hi <= lo) return;
		int j = partition(x, lo, hi); //returns the pivot's index
		//sort the two sub-arrays
		sort(x, lo, j-1);
		sort(x, j+1, hi);
	}
	
	/**
	 * partitions the array into two sub-arrays
	 * and puts the pivot to where the left elements are 
	 * smaller and right elements are greater
	 * @param x - the input array of products that needs be partitioned
	 * @param lo - index of the first element in input array
	 * @param hi - index of the last element in input array
	 * @return the index where the pivot is
	 * @see Quick#less(Comparable, Comparable)
	 * @see @link Quick#exch(Comparable[], int, int)
	 */
	private static int partition(Product[] x, int lo, int hi) {
		int i = lo, j = hi+1;
		Product v = x[lo];
		while(true) {
			while (less(x[++i], v)) {
				if(i == hi)
					break;
			}
			while(less(v, x[--j])) {
				if(j == lo)
					break;
			}
			if(i >= j) break;
			exch(x, i, j);
		}
		exch(x, lo, j);
		return j;
	}
	/**
	 * shuffles the array of products
	 * @param x - the input array
	 * @param n - the size of array
	 */
	//reference https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	public static void shuffle(Comparable[] x, int n){
		Random rnd = ThreadLocalRandom.current();
		for (int i = n - 1; i > 0; i-- ){
			int in = rnd.nextInt(i+1);
			Comparable t = x[in];
			x[in] = x[i];
			x[i] = t;
		}
	}
	/**
	 * Checks if v is less than w 
	 * @param v - the product that needs to be compared
	 * @param w - the other product that needs to be compared
	 * @return true if v should be located before w, false otherwise
	 * @see Product#compareTo(Product)
	 */
	private static boolean less(Comparable v, Comparable w) 
	{	return v.compareTo(w) < 0; }
	
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
	 * three partition quick sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 * @see Quick#threeSort(Comparable[], int, int)
	 */
	public static void sortThreePartition ( Comparable[] x, int n ) {
		shuffle(x, n);//shuffle for best performance
		threeSort(x, 0, n - 1);
		
	}
	/**
	 * partitions the array 
	 * duplicate elements are put together
	 * @param x - the input array that needs to be partitioned
	 * @param lo - index of the first element
	 * @param hi - index of the last element
	 * @see Product#compareTo(Product)
	 */
	private static void threeSort(Comparable[] x, int lo, int hi) {
		if(hi <= lo) return;
		int lt = lo, i = lo+1, gt = hi;
		Comparable v = x[lo]; // takes the first element as the pivot
		while(i <= gt) {
			int cmp = x[i].compareTo(v);
			if	(cmp < 0) exch(x, lt++, i++);
			else if	(cmp > 0) exch(x, i, gt--);
			else	i++;// goes to next element if there are duplicates in the array
		}
		//sort the sub-arrays recursively
		threeSort(x, lo, lt-1);
		threeSort(x, gt + 1, hi);
	}
	

}

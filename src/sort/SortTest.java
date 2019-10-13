package sort;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit test for all the implemented sorting algorithems
 * using the file a1_in.txt
 * @author Behdad Khamneli, Lab2
 * @see Heap
 * @see Insertion
 * @see Merge
 * @see Quick
 */
public class SortTest {
	
	
	/**
	 * array of products
	 * @see Product
	 */
	
	private Product[] p1 = new Product[(int)Math.pow(2, 4)];//first line of product
	private Product[] p2 = new Product[(int)Math.pow(2, 6)];//second line
	private Product[] p3 = new Product[(int)Math.pow(2, 8)];//third line
	private Product[] p4 = new Product[(int)Math.pow(2, 10)];//fourth line
	private Product[] p5 = new Product[(int)Math.pow(2, 12)];//fifth line
	
	private Product [][] productList = {p1, p2, p3, p4, p5};//eg. to get p1[1]-> productList[0][1]
	private String[] prods = {"p1", "p2", "p3", "p4", "p5"};// this is used later for string representation
	
	/**
	 * Generates products
	 * reads the data file and populates the product ADT
	 * @throws Exception if file could not be opened
	 * @see Product
	 */
	@Before
	public void setUp() throws Exception {
		try {
			BufferedReader input = new BufferedReader(new FileReader("./data/a1_in.txt"));
			
			String line = input.readLine();
			prodIn(p1, line);// initializes the p1(first set of products)
			line = input.readLine();// read the next line
			prodIn(p2, line);
			line = input.readLine();
			prodIn(p3, line);
			line = input.readLine();
			prodIn(p4, line);
			line = input.readLine();
			prodIn(p5, line);
			line = input.readLine();
			
			input.close();

			
		}catch(IOException ex) {
			System.out.println("File could not be opened!!");
		}

		
		
	}
	/**
	 * populates the product
	 * @param p - The input products that need be filled with the data
	 * @param line - the line of data
	 */
	private static void prodIn(Product[] p, String line) {
		
		StringTokenizer strToken = new StringTokenizer(line, "{},");// tokenizes the string(i.e. remove {},)
		int count = strToken.countTokens();// the number of elements -->eg 32 means 16 products
		for(int i = 0; i*2 < count; i++) {
			p[i] = new Product(strToken.nextToken(), Integer.parseInt(strToken.nextToken()));	
		}
		
		
	}
	/**
	 * Checks if x is sorted in ascending order
	 * uses method less to see if elements are sorted based on sales amount
	 * @param x is an array of type Comparable
	 * @return A boolean, true if the elements are in ascending order, false otherwise
	 */
	public static boolean isSorted(Comparable[] x) {
		for(int i = 1; i < x.length; i++)
			if(less(x[i], x[i-1])) return false;
		return true;
	}
	
	/**
	 * Checks if product ids with same amount of sales is sorted properly 
	 * @param x is an array of type Comparable 
	 * @return true if IDs with same amount of sale are sorted, false otherwise
	 */
	public static boolean isIdSorted(Product[] x) {// checks ids to make sure the products are sorted properly
		for(int i = 0; i < x.length-1; i++) {
			if(x[i].salesAmount() == x[i+1].salesAmount()) {
				if(x[i].prodId().compareToIgnoreCase(x[i+1].prodId()) > 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if v is less than w 
	 * @param v - the product that needs to be compared
	 * @param w - the other product that needs to be compared
	 * @return true if v should be located before w, false otherwise
	 * @see Product#compareTo(Product)
	 */
	private static boolean less(Comparable v, Comparable w) 
	{	return v.compareTo(w) < 0;	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test MergeTD
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see StopWatch 
	 * @see Merge#sortMergeTD(Comparable[], int)
	 */
	@Test
	public void testMergeTD() {
		
		System.out.print("\nMergeTD:");
		for (int i = 0; i < 5; i++) {// iterates for 5 products
			StopWatch stopwatch = new StopWatch();//initializes the stopwatch 
			Merge.sortMergeTD(productList[i], (int)Math.pow(2, 4 + 2*i));
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
		
	}
	
	/**
	 * Test MergeBU
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Merge#sortMergeTD(Comparable[], int)
	 * @see StopWatch 
	 */
	@Test
	public void testMergeBU() {
		System.out.print("\nMergeBU:");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Merge.sortMergeBU(productList[i], (int)Math.pow(2, 4 + 2*i));
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	/**
	 * Test basic insertion sort
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Insertion#sortInsert(Product[])
	 * @see StopWatch
	 */
	@Test
	public void testSortInsert(){
		
		System.out.print("\nInsertSort(not comparable):");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Insertion.sortInsert(productList[i]);
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	/**
	 * Tests the insertions sort(comparable)
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Insertion#sortComparable(Comparable[], int)
	 * @see StopWatch 
	 */
	@Test
	public void testInsertComparable(){
		
		System.out.print("\nInsertComparable:");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Insertion.sortComparable(productList[i], (int)Math.pow(2, 4 + 2*i));
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	/**
	 * Test InsertBinary
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with the same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Insertion#sortBinary(Comparable[], int)
	 * @see StopWatch 
	 */
	@Test
	public void testInsertBinary(){
		
		System.out.print("\nInsertBinary:");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Insertion.sortBinary(productList[i], (int)Math.pow(2, 4 + 2*i));
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	/**
	 * Test basic quick sort
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with the same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Quick#sortBasicQuick(Product[])
	 * @see StopWatch 
	 */
	@Test
	public void testBasicQuick(){
		
		System.out.print("\nBasic Quick:");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Quick.sortBasicQuick(productList[i]);
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	/**
	 * Test 3-way partitioning quick sort
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with the same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Quick#sortThreePartition(Comparable[], int)
	 * @see StopWatch
	 */
	@Test
	public void testThreePartition(){
		
		System.out.print("\nThreePartition Quick:");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Quick.sortThreePartition(productList[i], (int)Math.pow(2, 4 + 2*i));
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	/**
	 * Test InsertBinary
	 * Tests to see if the products are sorted by sales
	 * and ids are sorted for the products with the same sales
	 * and uses a stop watch to measure the elapsed time
	 * @see Heap#sortHeap(Comparable[], int)
	 * @see StopWatch
	 */
	@Test
	public void testHeap(){
		
		System.out.print("\nHeap sort:");
		for (int i = 0; i < 5; i++) {
			StopWatch stopwatch = new StopWatch();
			Heap.sortHeap(productList[i], (int)Math.pow(2, 4 + 2*i));
			System.out.print(" " + prods[i] + ":" + stopwatch.elapsedTime());
			assert(isSorted(productList[i])); // if products are sorted
			assert(isIdSorted(productList[i]));// if products with same sales are sorted properly
		}
	}
	
	

}

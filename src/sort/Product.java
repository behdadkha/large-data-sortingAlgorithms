package sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Product ADT, comparable type
 * @author Behdad Khamneli, Lab2
 * @version 1
 *
 */
public class Product implements Comparable<Product>{
	

	private final String id; //holds the Product's id
	private int sales; //holds the sales amount of the product
	
	/**
	 * Constructor for product
	 * initializes the id and sales amount of a product
	 * @param id - product's id
	 * @param sales - product's sales amount
	 */
	public Product(String id, int sales) {
		this.id = id;
		this.sales = sales;
	}
	
	/**
	 * retrieves the product's sales amount
	 * @return product's sales amount
	 */
	public int salesAmount() {
		return sales;
	}
	/**
	 * retrieves the product's id
	 * @return product's id
	 */
	public String prodId() {
		return id;
	}
	
	/**
	 * set product sales
	 * @param sales - the new sales amount
	 */
	public void setSales(int sales) {
		this.sales = sales;
	}
	/**
	 * @return String representation of a product, id followed by sales amount
	 */
	public String toString() {
		return String.format("%s %d", id, sales);
	}
	
	/**
	 * Compares two products
	 * if sales are equal compare the product ids
	 * @param j - a product that needs to be compared
	 * @return 1 if this product is bigger than product j, -1 if product j is bigger 
	 * and 0 if this product is equal to product j(sales and ids) 
	 */
	@Override
	public int compareTo(Product j)
	{
		if(this.sales == j.sales) {// if product have same sales, sort their id alphabetically
			return this.id.compareTo(j.id);
		}else {
			if(this.sales > j.sales) {
				return 1;
			}else {
				return -1;
			}
			
		}
		
	}
	
}

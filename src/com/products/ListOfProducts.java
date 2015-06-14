package com.products;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ListOfProducts {

	private ArrayList<Product> products;
	private ObjectOutputStream outPut;
	private ObjectInputStream inPut;

	
	public ListOfProducts() {
		products = new ArrayList<>();
	}

	public void add(Product p){
		products.add(p);
	}
	public String[] toArray() {
		String[] array = new String[products.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = products.get(i).toString();
		}
		return array;
	}
	
	public void toFile(String filename){
		try {
			String[] products = toArray();
			outPut = new ObjectOutputStream(new FileOutputStream(filename));
			for (int i = 0; i < products.length; i++) {
				outPut.writeObject(this.products.get(i));
				
			}
			outPut.close();
			
			//inPut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String fromFile(String filename) throws IOException{
		StringBuilder sb = new StringBuilder();
		try {
			
			inPut = new ObjectInputStream(new FileInputStream(filename));
			while(true){
				sb.append(inPut.readObject().toString());
			}
			
			
		} catch (IOException | ClassNotFoundException e) {
			inPut.close();
			return sb.toString();
		}
	}
	public double averagePrice(){
		return (double) products.stream().collect(Collectors.averagingDouble(Product::getPrice));
	}
	
	public void sortByPrice(Comparator<Product> comparator){
		products.sort(comparator);
	}
	
	@Override
	public String toString() {
		return "ListOfProducts [products=" + products.toString() + "]";
	}
	
	
}

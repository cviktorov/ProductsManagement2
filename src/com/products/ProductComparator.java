package com.products;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		int c = (int)(o1.getPrice() - o2.getPrice());
		return c;
	}
	
}

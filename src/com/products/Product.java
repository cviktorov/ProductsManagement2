package com.products;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private final int INV_NUMBER;
	private String invDescription;
	private double price;

	public Product() {
		this("", 0);
	}

	public Product(String invDescription, double price) {
		this.invDescription = invDescription;
		this.price = price;
		Product.count++;
		INV_NUMBER = count;
	}

	public Product(Product p) {
		this(p.invDescription, p.price);
	}

	public String getInvDescription() {
		return invDescription;
	}

	public void setInvDescription(String invDescription) {
		this.invDescription = invDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInvNumber() {
		return INV_NUMBER;
	}

	@Override
	public String toString() {

		return "Product [INV_NUMBER=" + INV_NUMBER + ", invDescription="
				+ invDescription + ", price=" + price + "]";
	}

}

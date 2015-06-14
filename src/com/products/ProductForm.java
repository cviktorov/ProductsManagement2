package com.products;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Comparator;

import javax.swing.JTextPane;

public class ProductForm extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtDescription;
	private JTextField txtPrice;
	private JLabel lblDescription;
	private JLabel lblPrice;
	private JButton btnAverage;
	private JButton btnShowProducts;
	private JButton btnSortProducts;
	private JButton btnWriteToFile;
	private JButton btnAddToList;
	private ListOfProducts listProducts;
	private JFileChooser fc;
	private JTextPane textArea;
	private JButton btnReadFromFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductForm frame = new ProductForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 261, 159);
		contentPane.add(panel);
		panel.setLayout(null);

		txtDescription = new JTextField();
		txtDescription.setBounds(105, 22, 144, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(105, 63, 144, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 25, 85, 14);
		panel.add(lblDescription);

		lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 66, 85, 14);
		panel.add(lblPrice);

		btnAddToList = new JButton("Add to list");
		btnAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = true;
				try {
					listProducts.add(new Product(txtDescription
							.getText(), Double.parseDouble(txtPrice
							.getText())));
				} catch (Exception e) {
					flag = false;
					textArea.setText("Wrong input \n");
				} 
				if(flag)textArea.setText("Product added \n");
			}
		});
		btnAddToList.setBounds(122, 102, 111, 23);
		panel.add(btnAddToList);

		btnSortProducts = new JButton("Sort products");
		btnSortProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Comparator<Product> comparator = new ProductComparator();
				listProducts.sortByPrice(comparator);
			}
		});
		btnSortProducts.setBounds(343, 11, 120, 23);
		contentPane.add(btnSortProducts);

		btnShowProducts = new JButton("Show products");
		btnShowProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(listProducts.toString());
			}
		});
		btnShowProducts.setBounds(343, 45, 120, 23);
		contentPane.add(btnShowProducts);

		btnAverage = new JButton("Average price");
		btnAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(Double.toString(listProducts.averagePrice()));
			}
		});
		btnAverage.setBounds(343, 79, 120, 23);
		contentPane.add(btnAverage);

		btnWriteToFile = new JButton("Write to file");
		btnWriteToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.showOpenDialog(null);
				listProducts.toFile(fc.getSelectedFile().toString());
			}
		});
		btnWriteToFile.setBounds(343, 113, 120, 23);
		contentPane.add(btnWriteToFile);
		
		textArea = new JTextPane();
		textArea.setBounds(10, 192, 453, 135);
		contentPane.add(textArea);
		
		btnReadFromFile = new JButton("Read from file");
		btnReadFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.showOpenDialog(null);
				try {
					textArea.setText(listProducts.fromFile(fc.getSelectedFile().toString()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReadFromFile.setBounds(343, 147, 120, 23);
		contentPane.add(btnReadFromFile);

		listProducts = new ListOfProducts();
	}
}

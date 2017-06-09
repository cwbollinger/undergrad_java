package edu.ncsu.csc216.gui.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ExampleGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btn1;
	private JLabel lbl1;
	private int numClicks;
	
	public ExampleGUI() {
		numClicks = 0;
		
		Container c = getContentPane();
		setTitle("ExampleGUI");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,4));
		
		c.setLayout(new BorderLayout());
		btn1 = new JButton("Click Me!");
		c.add(btn1, BorderLayout.NORTH);
		btn1.addActionListener(this);
		
		lbl1 = new JLabel("HELLO!!");
		panel.add(lbl1, BorderLayout.CENTER);

		c.add(panel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ExampleGUI();
	}

	public void actionPerformed(ActionEvent e) {
		numClicks++;
		lbl1.setText("I've been clicked "+numClicks+" times.");
		
	}
}

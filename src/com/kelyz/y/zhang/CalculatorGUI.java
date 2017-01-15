package com.kelyz.y.zhang;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class CalculatorGUI {
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			JFrame frame = new Calculator();
	     	frame.setTitle("Checksum Calculator");
	     	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     	frame.setVisible(true);
	     	frame.setResizable(true);
		});
	}
}

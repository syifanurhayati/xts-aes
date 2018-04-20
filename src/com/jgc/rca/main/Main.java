package com.jgc.rca.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// Create a simple GUI window
public class Main {
	private static void createWindow() {
		// Create and set up the window.
		JFrame frame = new JFrame("Main");
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel("Welcome to XTS-AES Program!", SwingConstants.CENTER);
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textLabel.setPreferredSize(new Dimension(300, 100));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.SOUTH);
		
		JButton btnEncrypt = new JButton("Encrypt");
		splitPane.setLeftComponent(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		splitPane.setRightComponent(btnDecrypt);
		splitPane.setResizeWeight(0.5);
		
		btnDecrypt.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
            	new DecryptMode();
            }
        });
		
		btnEncrypt.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
            	new EncryptMode();
            }
        });
		
		// Display the window.
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createWindow();
	}
}
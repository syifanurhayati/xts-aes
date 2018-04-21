package com.jgc.rca.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

// Create a simple GUI window
public class Main {
	private static void createWindow() {
		// Create and set up the window.
		JFrame frame = new JFrame("Main");
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setForeground(Color.BLACK);
		frame.setFont(new Font("Century Gothic", Font.BOLD, 19));
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[479.00px]", "[134.00px][69.00][62.00px]"));
		JLabel textLabel = new JLabel("Welcome to XTS-AES Program!", SwingConstants.CENTER);
		textLabel.setBackground(new Color(25, 25, 112));
		textLabel.setForeground(new Color(255, 255, 255));
		textLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 35));
		textLabel.setPreferredSize(new Dimension(300, 100));
		frame.getContentPane().add(textLabel, "cell 0 0,growx,aligny center");
		
		JLabel lblWhatDoYou = new JLabel("Do you want to?");
		lblWhatDoYou.setFont(new Font("Century Gothic", Font.ITALIC, 23));
		lblWhatDoYou.setForeground(Color.WHITE);
		frame.getContentPane().add(lblWhatDoYou, "cell 0 1,alignx center,aligny center");
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, "cell 0 2,growx,aligny center");
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		splitPane.setLeftComponent(btnEncrypt);
		btnEncrypt.setPreferredSize(new Dimension(50, 80));
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		splitPane.setRightComponent(btnDecrypt);
		btnDecrypt.setPreferredSize(new Dimension(50, 80));
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
		frame.setPreferredSize(new Dimension(500, 340));
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createWindow();
	}
}
package com.jgc.rca.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

public class ResultEncryptMode {
  JFrame frame = new JFrame();
  
  String selectedDir;
  String fileFilterPath = "F:/jdk1.5";
  private JButton btnEncryptNow;
  private JTextPane txtPreview;
  private JButton btnSeeTheResult;
  private JLabel lblFileWasSuccessful;
  private JLabel lblIWantTo;

  public ResultEncryptMode(String filePath, String keyPath, String resultPath, String result) {
    frame.getContentPane().setBackground(SystemColor.activeCaption);
    frame.setTitle("Encryption Mode");
    frame.setType(Type.POPUP);
    frame.setResizable(false);
    this.frame.setAlwaysOnTop(true);
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new MigLayout("", "[231.00px,grow][grow][]", "[31px,grow][grow][31px][grow][31px][31px]"));
    
    lblFileWasSuccessful = new JLabel("Encryption Success!");
    lblFileWasSuccessful.setForeground(Color.WHITE);
    lblFileWasSuccessful.setFont(new Font("Century Gothic", Font.BOLD, 20));
    frame.getContentPane().add(lblFileWasSuccessful, "cell 0 0,alignx center");
    
    lblIWantTo = new JLabel("I want to decrypt file back");
    lblIWantTo.setForeground(Color.WHITE);
    lblIWantTo.setFont(new Font("Century Gothic", Font.BOLD, 16));
    frame.getContentPane().add(lblIWantTo, "cell 2 0 1 5,alignx trailing,aligny center");
    
    txtPreview = new JTextPane();
    txtPreview.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    frame.getContentPane().add(txtPreview, "cell 0 4 1 2,grow");
    
    btnSeeTheResult = new JButton("See the result");
    btnSeeTheResult.setPreferredSize(new Dimension(80, 100));
    btnSeeTheResult.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    frame.getContentPane().add(btnSeeTheResult, "cell 0 2,alignx center");
    btnSeeTheResult.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
            txtPreview.setText(result);
        }
    });
    
    btnEncryptNow = new JButton();
    btnEncryptNow.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    btnEncryptNow.setText("Decrypt");
    this.frame.getContentPane().add(btnEncryptNow, "cell 2 5,grow");
    btnEncryptNow.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
        	frame.setVisible(false);
        	// call function decrypt
        	XTS solver;
			try {
				solver = new XTS(filePath, keyPath, resultPath);
				solver.decrypt();
				String result = solver.getResultContent();
	        	new ResultDecryptMode(filePath, keyPath, resultPath, result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    });
    
    frame.setPreferredSize(new Dimension(500, 340));
    frame.setLocationRelativeTo(null);
	frame.pack();
	frame.setVisible(true);
  }
}

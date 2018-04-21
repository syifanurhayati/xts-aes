package com.jgc.rca.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import java.awt.Window.Type;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

public class ResultDecryptMode {
  JFrame frame = new JFrame();
  
  String selectedDir;
  String fileFilterPath = "F:/jdk1.5";
  private JButton btnEncryptNow;
  private JTextPane txtPreview;
  private JButton btnSeeTheResult;
  private JLabel lblFileWasSuccessful;
  private JLabel lblIWantTo;

  public ResultDecryptMode(String file, String key, String result) {
    frame.getContentPane().setBackground(SystemColor.activeCaption);
    frame.setTitle("Decryption Mode");
    frame.setType(Type.POPUP);
    frame.setResizable(false);
    this.frame.setAlwaysOnTop(true);
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new MigLayout("", "[263.00px,grow][grow][]", "[31px,grow][grow][31px][grow][31px][31px]"));
    
    lblFileWasSuccessful = new JLabel("Decryption Success!");
    lblFileWasSuccessful.setForeground(Color.WHITE);
    lblFileWasSuccessful.setFont(new Font("Century Gothic", Font.BOLD, 19));
    frame.getContentPane().add(lblFileWasSuccessful, "cell 0 0,alignx center");
    
    lblIWantTo = new JLabel("I want to encrypt file back");
    lblIWantTo.setForeground(Color.WHITE);
    lblIWantTo.setFont(new Font("Century Gothic", Font.BOLD, 16));
    frame.getContentPane().add(lblIWantTo, "cell 2 0 1 5,alignx trailing,aligny center");
    
    txtPreview = new JTextPane();
    txtPreview.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    frame.getContentPane().add(txtPreview, "cell 0 4 1 2,grow");
    
    btnSeeTheResult = new JButton("See the result");
    btnSeeTheResult.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    frame.getContentPane().add(btnSeeTheResult, "cell 0 2,alignx center");
    btnSeeTheResult.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
            txtPreview.setText(result);
        }
    });
    
    btnEncryptNow = new JButton();
    btnEncryptNow.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    btnEncryptNow.setText("Encrypt");
    this.frame.getContentPane().add(btnEncryptNow, "cell 2 5,grow");
    btnEncryptNow.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
        	frame.setVisible(false);
        	// call function encrypt
        	String resultEnc = "ini hasil balikan encrypt";
        	new ResultEncryptMode(file, key, resultEnc);
        }
    });
    
    frame.setPreferredSize(new Dimension(500, 340));
    frame.setLocationRelativeTo(null);
	frame.pack();
	frame.setVisible(true);
  }
}

package com.jgc.rca.main;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EncryptMode {
  JFrame frame = new JFrame();
  JButton buttonSelectFileKey;
  JButton buttonSelectFile;
  
  String selectedDir;
  String fileFilterPath = "F:/jdk1.5";
  private JLabel lblFile;
  private JLabel lblKey;
  private JTextField text;
  private JTextField text_1;
  private JButton btnEncyptNow;

  public EncryptMode() {
    this.frame.setAlwaysOnTop(true);
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
    
    lblFile = new JLabel();
    lblFile.setText("File :");
    this.frame.getContentPane().add(lblFile);
    
    text = new JTextField();
    this.frame.getContentPane().add(text);
    
    buttonSelectFile = new JButton();
    buttonSelectFile.setText("Browse File");
    this.frame.getContentPane().add(buttonSelectFile);
    buttonSelectFile.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
        	JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);
            File chosen = openFile.getSelectedFile();
            text.setText(chosen.getAbsolutePath());
        }
    });
    
    lblKey = new JLabel();
    lblKey.setText("Key :");
    this.frame.getContentPane().add(lblKey);
    
    text_1 = new JTextField();
    this.frame.getContentPane().add(text_1);
    
    buttonSelectFileKey = new JButton();
    buttonSelectFileKey.setText("Browse File");
    this.frame.getContentPane().add(buttonSelectFileKey);
    buttonSelectFileKey.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
        	JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);
            File chosenKey = openFile.getSelectedFile();
            text_1.setText(chosenKey.getAbsolutePath());
        }
    });
    
    JLabel label_1 = new JLabel("");
    frame.getContentPane().add(label_1);
    
    JLabel label_2 = new JLabel("");
    frame.getContentPane().add(label_2);
    
    JLabel label = new JLabel();
    this.frame.getContentPane().add(label);
    
    JLabel label_3 = new JLabel();
    this.frame.getContentPane().add(label_3);
    
    JLabel label_4 = new JLabel();
    this.frame.getContentPane().add(label_4);
    
    btnEncyptNow = new JButton();
    btnEncyptNow.setText("Encrypt Now!");
    this.frame.getContentPane().add(btnEncyptNow);
    btnEncyptNow.addActionListener(new ActionListener( ) {
        public void actionPerformed(ActionEvent event) {
        	frame.setVisible(false);
        	new ResultEncryptMode();
        }
    });
    
    frame.setLocationRelativeTo(null);
	frame.pack();
	frame.setVisible(true);
  }
}

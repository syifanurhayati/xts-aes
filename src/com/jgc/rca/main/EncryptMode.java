package com.jgc.rca.main;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.miginfocom.swing.MigLayout;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;

public class EncryptMode {
	JFrame frame = new JFrame();
	JButton buttonSelectFileKey;
	JButton buttonSelectFile;

	String selectedDir;
	String fileFilterPath = "F:/jdk1.5";
	private JLabel lblFile;
	private JLabel lblKey;
	private JTextField filePath;
	private JTextField keyPath;
	private JButton btnEncryptNow;

	public EncryptMode() {
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setType(Type.POPUP);
		frame.setResizable(false);
		this.frame.setAlwaysOnTop(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane()
				.setLayout(new MigLayout("", "[68.00px][244.00px][134.00px]", "[43.00px][][31px][][51.00px][31px]"));

		lblFile = new JLabel();
		lblFile.setFont(new Font("Century Gothic", Font.BOLD, 22));
		lblFile.setForeground(new Color(255, 255, 255));
		lblFile.setText("File :");
		this.frame.getContentPane().add(lblFile, "cell 0 1,alignx center,growy");

		filePath = new JTextField();
		this.frame.getContentPane().add(filePath, "cell 1 1,grow");

		buttonSelectFile = new JButton();
		buttonSelectFile.setFont(new Font("Century Gothic", Font.PLAIN, 19));
		buttonSelectFile.setText("Browse File");
		this.frame.getContentPane().add(buttonSelectFile, "cell 2 1,alignx center,growy");
		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				File chosen = openFile.getSelectedFile();
				filePath.setText(chosen.getAbsolutePath());
			}
		});

		lblKey = new JLabel();
		lblKey.setFont(new Font("Century Gothic", Font.BOLD, 22));
		lblKey.setForeground(new Color(255, 255, 255));
		lblKey.setText("Key :");
		this.frame.getContentPane().add(lblKey, "cell 0 3,alignx center,growy");

		keyPath = new JTextField();
		this.frame.getContentPane().add(keyPath, "cell 1 3,grow");

		buttonSelectFileKey = new JButton();
		buttonSelectFileKey.setFont(new Font("Century Gothic", Font.PLAIN, 19));
		buttonSelectFileKey.setText("Browse File");
		this.frame.getContentPane().add(buttonSelectFileKey, "cell 2 3,alignx center,aligny center");
		buttonSelectFileKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				File chosenKey = openFile.getSelectedFile();
				keyPath.setText(chosenKey.getAbsolutePath());
			}
		});

		btnEncryptNow = new JButton();
		btnEncryptNow.setFont(new Font("Century Gothic", Font.PLAIN, 19));
		btnEncryptNow.setText("Encrypt Now!");
		this.frame.getContentPane().add(btnEncryptNow, "cell 2 5,grow");
		btnEncryptNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ((filePath.getText().equals("")) || (keyPath.getText().equals(""))) {
					JOptionPane.showMessageDialog(frame, "File and key can not be empty");
				} else {
					frame.setVisible(false);
					try {
						FileReader fr = new FileReader(keyPath.getText());
						BufferedReader reader = new BufferedReader(fr);
						String key = reader.readLine();

						// cek length dari key
						if (key.length() == 64 && key.matches("-?[0-9a-fA-F]+")) {

							String resultPath = "";
							JFileChooser chooser = new JFileChooser();
							int retrival = chooser.showSaveDialog(null);
							if (retrival == JFileChooser.APPROVE_OPTION) {
								resultPath = chooser.getSelectedFile().getAbsolutePath();
							}

							// call function encrypt
							System.out.println(resultPath);
							// encrypt(resultPath)
							String file = "ceritanya isi file";
							key = "ceritanya isi key";
							String result = "ceritanya isi result";
							frame.setVisible(false);
							new ResultEncryptMode(file, key, result);

						} else {
							JOptionPane.showMessageDialog(null, "Key File must be filled with 64 digits Hex",
									"Wrong Input!", JOptionPane.INFORMATION_MESSAGE);
						}

						reader.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		frame.setPreferredSize(new Dimension(500, 340));
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}
}

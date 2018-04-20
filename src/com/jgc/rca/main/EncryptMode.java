package com.jgc.rca.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;

public class EncryptMode {
  Display display = new Display();
  Shell shlEncryptMode = new Shell(display);
  Button buttonSelectFileKey;
  Button buttonSelectFile;
  
  String selectedDir;
  String fileFilterPath = "F:/jdk1.5";
  private Label lblFile;
  private Label lblKey;
  private Text text;
  private Text text_1;
  private Button btnEncyptNow;

  public EncryptMode() {
    shlEncryptMode.setTouchEnabled(true);
    shlEncryptMode.setText("Encrypt Mode");
    shlEncryptMode.setMinimumSize(new Point(240, 240));
    shlEncryptMode.setLayout(new GridLayout(3, false));
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);

    shlEncryptMode.pack();
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    
    lblFile = new Label(shlEncryptMode, SWT.NONE);
    lblFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblFile.setText("File");
    
    text = new Text(shlEncryptMode, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    buttonSelectFile = new Button(shlEncryptMode, SWT.PUSH);
    buttonSelectFile.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent e) {
    	}
    });
    buttonSelectFile.setText("Browse File");
    buttonSelectFile.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        FileDialog fileDialog = new FileDialog(shlEncryptMode, SWT.MULTI);

        fileDialog.setFilterPath(fileFilterPath);
        
        fileDialog.setFilterExtensions(new String[]{"*.rtf", "*.html", "*.*"});
        fileDialog.setFilterNames(new String[]{ "Rich Text Format", "HTML Document", "Any"});
        
        String firstFile = fileDialog.open();

        if(firstFile != null) {
          fileFilterPath = fileDialog.getFilterPath();
          String[] selectedFiles = fileDialog.getFileNames();
          StringBuffer sb = new StringBuffer("Selected files under dir " + fileDialog.getFilterPath() +  ": \n");
          for(int i=0; i<selectedFiles.length; i++) {
            sb.append(selectedFiles[i] + "\n");
          }
          lblFile.setText(sb.toString());
        }
      }
    });
    
    lblKey = new Label(shlEncryptMode, SWT.NONE);
    lblKey.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblKey.setText("Key");
    
    text_1 = new Text(shlEncryptMode, SWT.BORDER);
    text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    
    buttonSelectFileKey = new Button(shlEncryptMode, SWT.PUSH);
    buttonSelectFileKey.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent e) {
    	}
    });
    buttonSelectFileKey.setText("Browse File");
    buttonSelectFileKey.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        FileDialog fileDialog = new FileDialog(shlEncryptMode, SWT.MULTI);

        fileDialog.setFilterPath(fileFilterPath);
        
        fileDialog.setFilterExtensions(new String[]{"*.rtf", "*.html", "*.*"});
        fileDialog.setFilterNames(new String[]{ "Rich Text Format", "HTML Document", "Any"});
        
        String firstFile = fileDialog.open();

        if(firstFile != null) {
          fileFilterPath = fileDialog.getFilterPath();
          String[] selectedFiles = fileDialog.getFileNames();
          StringBuffer sb = new StringBuffer("Selected files under dir " + fileDialog.getFilterPath() +  ": \n");
          for(int i=0; i<selectedFiles.length; i++) {
            sb.append(selectedFiles[i] + "\n");
          }
          lblFile.setText(sb.toString());
        }
      }
    });
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    new Label(shlEncryptMode, SWT.NONE);
    
    Listener listener = new Listener() {
    	public void handleEvent(Event arg0) {
			shlEncryptMode.setVisible(false);
			new ResultEncryptMode();
		}
      };
    
    btnEncyptNow = new Button(shlEncryptMode, SWT.NONE);
    btnEncyptNow.setText("Encrypt Now!");
    btnEncyptNow.addListener(SWT.Selection, listener);
    shlEncryptMode.open();

    // Set up the event loop.
    while (!shlEncryptMode.isDisposed()) {
      if (!display.readAndDispatch()) {
        // If no more entries in event queue
        display.sleep();
      }
    }

    display.dispose();
  }
}

package com.jgc.rca.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Point;

public class ResultEncryptMode {
  Display display = new Display();
  Shell shlResultEncrypt = new Shell(display);
  
  String selectedDir;
  String fileFilterPath = "F:/jdk1.5";
  private Label lblFile;
  private Label lblKey;
  private Button btnEncyptNow;
  private Button btnSeeTheResult;
  private Label lblIWantTo;

  public ResultEncryptMode() {
    shlResultEncrypt.setTouchEnabled(true);
    shlResultEncrypt.setText("Encrypt Mode");
    shlResultEncrypt.setMinimumSize(new Point(240, 240));
    shlResultEncrypt.setLayout(new GridLayout(3, false));
    new Label(shlResultEncrypt, SWT.NONE);

    shlResultEncrypt.pack();
    new Label(shlResultEncrypt, SWT.NONE);
    new Label(shlResultEncrypt, SWT.NONE);
    new Label(shlResultEncrypt, SWT.NONE);
    new Label(shlResultEncrypt, SWT.NONE);
    new Label(shlResultEncrypt, SWT.NONE);
    
    lblFile = new Label(shlResultEncrypt, SWT.NONE);
    lblFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblFile.setText("Successful!");
    new Label(shlResultEncrypt, SWT.NONE);
    
    lblIWantTo = new Label(shlResultEncrypt, SWT.NONE);
    lblIWantTo.setText("I want to decrypt file back");
    
    btnSeeTheResult = new Button(shlResultEncrypt, SWT.NONE);
    btnSeeTheResult.setText("See the result");
    new Label(shlResultEncrypt, SWT.NONE);
    
    btnEncyptNow = new Button(shlResultEncrypt, SWT.NONE);
    btnEncyptNow.setText("Decrypt");
    btnEncyptNow.addListener(SWT.Selection, new Listener() {
		@Override
		public void handleEvent(Event arg0) {
//			shlResultEncrypt.setVisible(false);
			new DecryptMode();
		}
	});
    
    lblKey = new Label(shlResultEncrypt, SWT.NONE);
    lblKey.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblKey.setText("Preview");
    new Label(shlResultEncrypt, SWT.NONE);
    new Label(shlResultEncrypt, SWT.NONE);
    shlResultEncrypt.open();

    // Set up the event loop.
    while (!shlResultEncrypt.isDisposed()) {
      if (!display.readAndDispatch()) {
        // If no more entries in event queue
        display.sleep();
      }
    }

    display.dispose();
  }
}

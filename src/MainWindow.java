import java.awt.*;
import javax.swing.*;

public class MainWindow {

	private JFrame frmfinger;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmfinger.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmfinger = new JFrame();
		frmfinger.setTitle("1Finger");
		frmfinger.setBounds(100, 100, 450, 300);
		frmfinger.setResizable(true);
		frmfinger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel logo_img = new JLabel("");
		int width = 200;
		int height = 100;
		frmfinger.getContentPane().setLayout(new BorderLayout(0, 0));
		frmfinger.getContentPane().setBackground(new Color(43,45,66));
		logo_img.setHorizontalAlignment(SwingConstants.CENTER);
//		logo_img
//				.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Farida\\Documents\\CSUI\\Me\\PORTOFOLIO\\Java\\OneFinger\\onefinger-raspberry\\assets\\logo_white.png")
//						.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		
		frmfinger.getContentPane().add(logo_img);
	}

}
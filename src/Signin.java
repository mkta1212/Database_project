import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Signin {

	private JFrame frame;
	private JTextField txtUserID;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtField;
	private JTextField txtUserName;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Signin window = new Signin();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Signin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("mouse.png");
		Cursor cu = tk.createCustomCursor(img, new Point(16, 16), "stick");
		Image img2 = tk.getImage("mouseclick.png");
		Cursor cu2 = tk.createCustomCursor(img2, new Point(16, 16), "stick");
		frame.setCursor(cu);
		//
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.setCursor(cu);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.setCursor(cu2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		ImagePanel bgImagePanel = new ImagePanel("background2.png");
		bgImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(bgImagePanel);
		//
		ImageIcon logoIcon = new ImageIcon("logo.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
//		JLabel logoLabel = new JLabel();
//		logoLabel.setIcon(logoIcon);
//		logoLabel.setBounds(380, 130, 200, 200);
		JButton logoButton = new JButton();
		logoButton.setContentAreaFilled(false);
		logoButton.setBorderPainted(false);
		logoButton.setIcon(logoIcon);
		logoButton.setBounds(385, 210, 50, 50);
		frame.getContentPane().add(logoButton);
		class eggListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Egg");
				frame.setLocation(400, 200);
				ImagePanel bgImagePanel = new ImagePanel("egg.png");
				bgImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
				frame.setContentPane(bgImagePanel);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle bounds = new Rectangle(screenSize);
				frame.setBounds(bounds);
//				frame.setBounds(450, 160, 450, 300);	
			}
		}
		ActionListener logoActionListener = new eggListener();
		logoButton.addActionListener(logoActionListener);
//		frame.getContentPane().add(logoLabel);
		//
		txtPassword = new JTextField();
//		txtPassword.setText("Password");
		txtPassword.setBounds(268, 127, 130, 26);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		txtUserID = new JTextField();
//		txtUsername.setText("Username");
		txtUserID.setBounds(268, 19, 130, 26);
		frame.getContentPane().add(txtUserID);
		txtUserID.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(230, 23, 26, 16);
		lblNewLabel.setFont(new Font("title",Font.BOLD,16));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("title",Font.BOLD,16));
		lblNewLabel_1.setBounds(170, 131, 100, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBounds(268, 220, 76, 28);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setFont(new Font("title",Font.BOLD,16));
		lblNewLabel_2.setBounds(203, 95, 61, 17);
		frame.getContentPane().add(lblNewLabel_2);

		txtEmail = new JTextField();
		txtEmail.setBounds(268, 91, 130, 26);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Confirm:");
		lblNewLabel_3.setFont(new Font("title",Font.BOLD,16));
		lblNewLabel_3.setBounds(183, 167, 100, 16);
		frame.getContentPane().add(lblNewLabel_3);

		txtField = new JTextField();
		txtField.setBounds(268, 163, 130, 26);
		frame.getContentPane().add(txtField);
		txtField.setColumns(10);

		JButton backBtn = new JButton("");
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login login = new Login();
			}
		}
		backBtn.addActionListener(new backListener());
		backBtn.setBounds(0, 0, 45, 23);
		//
		backBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		backBtn.setForeground(Color.WHITE);
//		notJoinBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFont(new Font("button", Font.BOLD, 13));
		backBtn.setForeground(Color.WHITE);
		ImageIcon backIcon = new ImageIcon("back.png");
		backIcon.setImage(backIcon.getImage().getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH));
		backBtn.setIcon(backIcon);
		//
		backBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
//				alreadyJoinBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setContentAreaFilled(false);
				backBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				backBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		frame.getContentPane().add(backBtn);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(268, 55, 130, 26);
		frame.getContentPane().add(txtUserName);

		JLabel lblNewLabel_2_1 = new JLabel("Name:");
		lblNewLabel_2_1.setFont(new Font("title",Font.BOLD,16));
		lblNewLabel_2_1.setBounds(204, 59, 61, 17);
		frame.getContentPane().add(lblNewLabel_2_1);
		frame.setVisible(true);
		class SigninBtn implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				boolean idValid = !txtUserID.getText().equals("");
				boolean nameValid = !txtUserName.getText().equals("");
				boolean emailValid = txtEmail.getText().matches(EMAIL_REGEX);
				boolean passwordValid = !txtPassword.getText().equals("")
						&& txtPassword.getText().equals(txtField.getText());
				if (idValid && nameValid && emailValid && passwordValid) {
					Connection_sql conn = new Connection_sql();
					if (conn.signUp(txtUserID.getText(), txtUserName.getText(), txtEmail.getText(),
							txtPassword.getText())) {
						frame.dispose();
						Login login = new Login();
					} else {
						txtUserID.setText("");
						txtUserName.setText("");
						txtEmail.setText("");
						txtPassword.setText("");
					}
				} else {
					if (!idValid) {
						JOptionPane.showMessageDialog(null, "Please enter user ID");
					} else if (!nameValid) {
						JOptionPane.showMessageDialog(null, "Please enter user name");
					} else if (!emailValid) {
						JOptionPane.showMessageDialog(null, "Email format error");
					} else if (txtPassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter password");
					} else if (!txtPassword.getText().equals(txtField.getText())) {
						JOptionPane.showMessageDialog(null, "Please check your confirmed password again");
					}
				}
			}
		}
		btnNewButton.addActionListener(new SigninBtn());
		btnNewButton.setBorder(BorderFactory.createLoweredBevelBorder());
//		backBtn.setForeground(Color.WHITE);
//		notJoinBtn.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("button", Font.BOLD, 13));
		btnNewButton.setForeground(Color.WHITE);
		//
		btnNewButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
//				alreadyJoinBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setContentAreaFilled(false);
				btnNewButton.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				btnNewButton.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
	}
}

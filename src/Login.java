import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class Login {

	private JFrame frame;
	private JPasswordField txtPassword;
	private JTextField txtUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
//		Music music = new Music();
//		music.playS("music//bgmusic.wav");
		initialize();
//		Cursor cursor=frame.getCursor().cursorToolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("image/pencil.gif").getImage(),
//				new Point(10, 20), "stick");;
//		frame.setCursor(Cursor.CROSSHAIR_CURSOR);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//
		frame = new JFrame("Log in");
		ImagePanel bgImagePanel = new ImagePanel("background2.png");
		bgImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(bgImagePanel);
		frame.setBounds(100, 100, 450, 300);
//		frame.setBounds(350, 180, 450, 300);
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
		ImageIcon logoIcon = new ImageIcon("logo.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
//		JLabel logoLabel = new JLabel();
//		logoLabel.setIcon(logoIcon);
//		logoLabel.setBounds(380, 130, 200, 200);
//		frame.getContentPane().add(logoLabel);
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
				frame.setLocation(380, 180);
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
		//

		JButton loginBtn = new JButton("Log in");
		loginBtn.setFont(new Font("button", Font.BOLD, 13));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setBounds(268, 163, 76, 28);
		frame.getContentPane().add(loginBtn);
		class loginListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (!txtUserName.getText().equals("") && !txtPassword.getText().equals("")) {
//					System.out.println(txtUserName.getText()+"\n"+txtPassword.getText());
					Connection_sql conn = new Connection_sql(txtUserName.getText(), txtPassword.getText());
					User user = conn.verify();
					if (user != null) {
						if (user.getIdentity().equals("User")) {
							if (user.getValidity() == 1) {
								frame.dispose();
								try {
									Homepage homepage = new Homepage(conn);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							} else {
								JOptionPane.showMessageDialog(null, "This account has been blocked");
							}
						} else if (user.getIdentity().equals("Manager")) {
							frame.dispose();
							managerpage managerPage = new managerpage(conn);

						}
					} else {
						JOptionPane.showMessageDialog(null, "invalid id or password");
						txtUserName.setText("");
						txtPassword.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "invalid id or password");
					txtUserName.setText("");
					txtPassword.setText("");
				}
			}
		}
		loginBtn.addActionListener(new loginListener());
		//
		loginBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setContentAreaFilled(false);
				loginBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				loginBtn.setFont(new Font("mouseListener", Font.BOLD, 15));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//

		txtPassword = new JPasswordField();
//		txtPassword.setText("Password");
		txtPassword.setBounds(280, 106, 130, 26);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		txtUserName = new JTextField();
//		txtUserName.setText("Username");
		txtUserName.setBounds(280, 50, 130, 26);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("title", Font.BOLD, 15));
		lblNewLabel.setBounds(230, 55, 26, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("title", Font.BOLD, 15));
		lblNewLabel_1.setBounds(175, 112, 100, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("If you don't have an accounct click");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 12));
//		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(230, 189, 227, 28);
		frame.getContentPane().add(lblNewLabel_2);

		JButton signinBtn = new JButton("Sign in");
		//
		signinBtn.setFont(new Font("button", Font.BOLD, 13));
		signinBtn.setForeground(Color.WHITE);
		signinBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		signinBtn.setContentAreaFilled(false);
		//
		signinBtn.setBounds(268, 220, 76, 28);
		frame.getContentPane().add(signinBtn);
		class signinListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				Signin window = new Signin();
				frame.dispose();
			}
		}
		signinBtn.addActionListener(new signinListener());
		//
		signinBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setContentAreaFilled(false);
				signinBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				signinBtn.setFont(new Font("mouseListener", Font.BOLD, 15));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		frame.setVisible(true);
		//
		frame.getRootPane().setDefaultButton(loginBtn);
	}
}

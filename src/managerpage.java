import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class managerpage {

	private JFrame frame;
	private Connection_sql conn_sql;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					managerpage window = new managerpage();
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
	public managerpage(Connection_sql conn_sql) {
		this.conn_sql = conn_sql;
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
		ImagePanel bggImagePanel = new ImagePanel("background2.png");
		bggImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(bggImagePanel);
		frame.setTitle("Manager");
		//
		JLabel lblNewLabel = new JLabel("Personal Profile");
		lblNewLabel.setFont(new Font("detail", Font.BOLD, 16));
//		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 47, 300, 18);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("detail", Font.BOLD, 17));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 73, 61, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("detail", Font.BOLD, 17));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(20, 99, 61, 20);
		frame.getContentPane().add(lblNewLabel_2);

		JButton blockBtn = new JButton("Block (users)");
		class BlockListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				blockuserpage blockUserPage = new blockuserpage(conn_sql);
				frame.dispose();
			}
		}
		blockBtn.addActionListener(new BlockListener());
		blockBtn.setToolTipText("z");
		blockBtn.setBounds(64, 155, 137, 48);
		frame.getContentPane().add(blockBtn);
		//
//		notJoinBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		blockBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		blockBtn.setContentAreaFilled(false);
		blockBtn.setFont(new Font("button", Font.BOLD, 13));
		blockBtn.setForeground(Color.WHITE);
		//
		blockBtn.addMouseListener(new MouseListener() {

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
				blockBtn.setFont(new Font("button", Font.BOLD, 15));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				blockBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//

		JButton cancelBtn = new JButton("Cancel (events)");
		class CancelListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					ManageEventPage managenEventPage = new ManageEventPage(conn_sql);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				;
			}

		}
		cancelBtn.addActionListener(new CancelListener());
		cancelBtn.setBounds(240, 155, 137, 48);
		//
		cancelBtn.addMouseListener(new MouseListener() {

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
				cancelBtn.setFont(new Font("button", Font.BOLD, 15));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				cancelBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		frame.getContentPane().add(cancelBtn);
		//
//		notJoinBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		cancelBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setFont(new Font("button", Font.BOLD, 13));
		cancelBtn.setForeground(Color.WHITE);
		//
		User user = conn_sql.getUser();
		JLabel label = new JLabel(user.getID());
		label.setBounds(91, 74, 130, 20);
		label.setFont(new Font("detail", Font.BOLD, 17));
		label.setForeground(Color.WHITE);
		frame.getContentPane().add(label);

		JLabel lblNewLabel_3 = new JLabel(user.getName());
		lblNewLabel_3.setBounds(91, 100, 130, 20);
		lblNewLabel_3.setFont(new Font("detail", Font.BOLD, 17));
		lblNewLabel_3.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_3);
		JMenu menu = new JMenu("Function List");
		JMenuItem logoutItem = new JMenuItem("Log out");
		class logoutListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				frame.dispose();
			}
		}
		logoutItem.addActionListener(new logoutListener());
		JMenuBar menuBar = new JMenuBar();
		menu.add(logoutItem);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		frame.setVisible(true);
	}
}

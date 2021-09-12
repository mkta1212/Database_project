import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class myeventPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					myeventPage window = new myeventPage();
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
	public myeventPage(Connection_sql conn) {
		initialize(conn);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection_sql conn) {
		User user = conn.getUser();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("My Events");
		ImagePanel bggImagePanel = new ImagePanel("background2.png");
		bggImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(bggImagePanel);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("title", Font.BOLD, 16));
		lblNewLabel.setBounds(45, 42, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("title", Font.BOLD, 16));
		lblNewLabel_1.setBounds(18, 68, 100, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JButton holderBtn = new JButton("Holder");
		class HolderListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					holderpage holderPage = new holderpage(conn);
					frame.dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				frame.dispose();
			}
		}
		holderBtn.addActionListener(new HolderListener());
		holderBtn.setBounds(18, 119, 127, 116);
//		notJoinBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		holderBtn.setContentAreaFilled(false);
		holderBtn.setFont(new Font("button", Font.BOLD, 13));
		holderBtn.setForeground(Color.WHITE);
		//
		holderBtn.addMouseListener(new MouseListener() {

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
				holderBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				holderBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});

		//
		frame.getContentPane().add(holderBtn);

		JButton notJoinBtn = new JButton("Event(Not yet)");
		notJoinBtn.setFont(new Font("button", Font.BOLD, 13));
		notJoinBtn.setForeground(Color.WHITE);
		notJoinBtn.setBounds(155, 119, 127, 116);
		notJoinBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		notJoinBtn.setContentAreaFilled(false);
		//
		notJoinBtn.addMouseListener(new MouseListener() {

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
				notJoinBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				notJoinBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		frame.getContentPane().add(notJoinBtn);
		class NotJoinListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					notjoinpage notJoin = new notjoinpage(conn);
					frame.dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				frame.dispose();
			}
		}
		notJoinBtn.addActionListener(new NotJoinListener());

		JButton alreadyJoinBtn = new JButton("Event(Already)");
		alreadyJoinBtn.setBounds(292, 119, 127, 116);
//		notJoinBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		alreadyJoinBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		alreadyJoinBtn.setContentAreaFilled(false);
		alreadyJoinBtn.setFont(new Font("button", Font.BOLD, 13));
		alreadyJoinBtn.setForeground(Color.WHITE);
		//
		alreadyJoinBtn.addMouseListener(new MouseListener() {

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
				alreadyJoinBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				alreadyJoinBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		frame.getContentPane().add(alreadyJoinBtn);
		frame.setVisible(true);
		class AlreadyJoinListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					alreadyjoin alreadyJoin = new alreadyjoin(conn);
					frame.dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				frame.dispose();
			}
		}
		alreadyJoinBtn.addActionListener(new AlreadyJoinListener());
		JButton backBtn = new JButton();
		Dimension dimension = new Dimension(backBtn.getSize());
		ImagePanel bgImagePanel = new ImagePanel("back.png");
		bgImagePanel.setBounds(0, 0, backBtn.getWidth(), backBtn.getHeight());
		backBtn.add(bgImagePanel);
//		backBtn.setIcon(image);
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					Homepage homePage = new Homepage(conn);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		backBtn.addActionListener(new backListener());
		backBtn.setBounds(0, 0, 45, 23);
		//
//		backBtn.setBorder(BorderFactory.createLoweredBevelBorder()); 
//		notJoinBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
//		backBtn.setFont(new Font("button", Font.BOLD, 13));
//		backBtn.setForeground(Color.WHITE);
		//
		frame.getContentPane().add(backBtn);
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
		JLabel idLabel = new JLabel(user.getID());
		idLabel.setFont(new Font("title", Font.BOLD, 16));
		idLabel.setBounds(89, 69, 99, 15);
		frame.getContentPane().add(idLabel);

		JLabel nameLabel = new JLabel(user.getName());
		nameLabel.setFont(new Font("title", Font.BOLD, 16));
		nameLabel.setBounds(89, 43, 99, 15);
		frame.getContentPane().add(nameLabel);

	}
}

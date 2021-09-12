import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class createevent {

	private JFrame frame;
	private JTextField titleField;
	private JTextField locationField;
	private JTextField contentField;
	private Connection_sql conn;
	private boolean valid = false;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					createevent window = new createevent();
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
	public createevent(Connection_sql conn) {
		this.conn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public boolean initialize() {
		frame = new JFrame();
		frame.setTitle("Create Page");
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//
		ImagePanel bgImagePanel = new ImagePanel("background2.png");
		bgImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(bgImagePanel);
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
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setFont(new Font("title",Font.BOLD,14));
		lblNewLabel.setBounds(6, 27, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		titleField = new JTextField();
		titleField.setBounds(54, 23, 178, 26);
		frame.getContentPane().add(titleField);
		titleField.setColumns(10);

		JButton backBtn = new JButton("X");
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
		frame.getContentPane().add(backBtn);
		//
//		notJoinBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		backBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFont(new Font("button", Font.BOLD, 13));
		backBtn.setForeground(Color.WHITE);
		//
		JLabel lblNewLabel_1 = new JLabel("Time:");
		lblNewLabel_1.setFont(new Font("title",Font.BOLD,14));
		lblNewLabel_1.setBounds(6, 65, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Location:");
		lblNewLabel_2.setFont(new Font("title",Font.BOLD,14));
		lblNewLabel_2.setBounds(6, 106, 70, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Detail:");
		lblNewLabel_3.setFont(new Font("title",Font.BOLD,14));
		lblNewLabel_3.setBounds(6, 145, 61, 16);
		frame.getContentPane().add(lblNewLabel_3);

		locationField = new JTextField();
		locationField.setBounds(70, 101, 344, 26);
		frame.getContentPane().add(locationField);
		locationField.setColumns(10);

		JTextArea textArea = new JTextArea(100, 20);
//		textArea.setBounds(407, 157, 329, 93);
		textArea.setLineWrap(true);
		textArea.setBounds(54, 145, 329, 85);
		//
		Border border = BorderFactory.createEtchedBorder(Color.ORANGE, Color.LIGHT_GRAY);
		textArea.setBorder(border);
		//
		frame.getContentPane().add(textArea);
//		contentField = new JTextField();
//		frame.getContentPane().add(contentField);
//		contentField.setColumns(10);

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(383, 145, 15, 85);
//		scrollPane.setLocation(383, 145);
//		scrollPane.add(textArea);
//		frame.getContentPane().add(scrollPane);	
		JButton createBtn = new JButton("Submit");
		createBtn.setBounds(169, 243, 117, 29);
		//
		createBtn.addMouseListener(new MouseListener() {

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
				createBtn.setFont(new Font("button", Font.BOLD, 13));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				createBtn.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
//		notJoinBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		createBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//		notJoinBtn.setBorderPainted(false);
		createBtn.setContentAreaFilled(false);
		createBtn.setFont(new Font("button", Font.BOLD, 13));
		createBtn.setForeground(Color.WHITE);
		//
		JComboBox<Integer> yearBox = new JComboBox<Integer>();
		yearBox.setBounds(54, 60, 74, 26);
		for (int year = 2020; year <= 2030; year++) {
			yearBox.addItem(year);
		}
		frame.getContentPane().add(yearBox);

		JComboBox<Integer> monthBox = new JComboBox<Integer>();
		monthBox.setBounds(138, 60, 54, 26);
		for (int month = 1; month <= 12; month++) {
			monthBox.addItem(month);
		}
		frame.getContentPane().add(monthBox);

		JComboBox<Integer> dayBox = new JComboBox<Integer>();
		dayBox.setBounds(202, 59, 54, 26);
		for (int day = 1; day <= 31; day++) {
			dayBox.addItem(day);
		}
		frame.getContentPane().add(dayBox);

		class CreateListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				int year = (int) yearBox.getSelectedItem();
				int month = (int) monthBox.getSelectedItem();
				int day = (int) dayBox.getSelectedItem();
				if (!titleField.getText().equals("") && !locationField.getText().equals("")
						&& !textArea.getText().equals("")) {
					if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
						JOptionPane.showMessageDialog(null, "Unknown date");
					} else {
						if (month == 2 && (day == 29 || day == 30)) {
							JOptionPane.showMessageDialog(null, "Unknown date");
						} else {
							try {
								String date = year + "/" + month + "/" + day;
								SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
								String eventTime = date;
								Date current = new Date();
								String currentTime = sdFormat.format(current);
								Date eventDate = sdFormat.parse(eventTime);
								Date currentDate = sdFormat.parse(currentTime);

								if (eventDate.compareTo(currentDate) == 1) {
									if (conn.createEvent(titleField.getText(), date, locationField.getText(),
											textArea.getText())) {
										Homepage homePage = new Homepage(conn);
										valid = true;
										frame.dispose();
									}
								} else {
									JOptionPane.showMessageDialog(null, "Invalid date");
								}

							} catch (Exception e1) {

							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please fullfill all space");
				}
			}
		}
		createBtn.addActionListener(new CreateListener());
		frame.getContentPane().add(createBtn);

		frame.setVisible(true);
		return valid;
	}

}

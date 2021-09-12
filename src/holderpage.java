import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.NonReadableChannelException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class holderpage extends JFrame {

	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton lastPageButton;
	private JLabel logoUnderLineLabel;
	private Connection_sql conn;
	private static final Font TItle_FONT = new Font("activity", Font.BOLD, 20);
	private static final Font REAIND_FONT = new Font("activity", Font.BOLD, 18);
	private static final int ICON_WIDTH = 55;
	private static final int ICON_HEIGHT = 45;
	private static final int FRAME_WIDTH = 520;
	private static final int FRAME_HEIGHT = 510;
	// 資料庫帳密碼那些等你建好打你的上去 ,port & database記得改

	public holderpage(Connection_sql conn) throws ParseException {
		this.conn = conn;
		this.panel = new JPanel();
		this.scrollPane = new JScrollPane(panel);
		panel.add(createTitlePanel());
		try {
			createEventPanel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(scrollPane);
		setVisible(true);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Holder");
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("mouse.png");
		Cursor cu = tk.createCustomCursor(img, new Point(16, 16), "stick");
		Image img2 = tk.getImage("mouseclick.png");
		Cursor cu2 = tk.createCustomCursor(img2, new Point(16, 16), "stick");
		setCursor(cu);
		//
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(cu);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(cu2);
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
	}

	public JPanel createTitlePanel() {
		this.lastPageButton = new JButton("");
		this.lastPageButton.setBounds(0, 0, 45, 23);
		//
		this.lastPageButton.setBorder(BorderFactory.createLoweredBevelBorder());
//		backBtn.setForeground(Color.WHITE);
//		notJoinBtn.setBorderPainted(false);
		this.lastPageButton.setContentAreaFilled(false);
		this.lastPageButton.setFont(new Font("button", Font.BOLD, 13));
//		this.lastPageButton.setForeground(Color.WHITE);
		ImageIcon backIcon = new ImageIcon("back.png");
		backIcon.setImage(backIcon.getImage().getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH));
		lastPageButton.setIcon(backIcon);
		class LastPageListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				myeventPage frame = new myeventPage(conn);
				dispose();
			}
		}
		lastPageButton.addActionListener(new LastPageListener());
		this.logoUnderLineLabel = new JLabel(
				" ________________________________________________________________________");
//		JPanel northPanel = new JPanel();
//		northPanel.setLayout(new BorderLayout());
//		northPanel.add(lastPageButton);
//		JPanel centerPanel = new JPanel();
//		centerPanel.setLayout(new BorderLayout());
//		centerPanel.add(logoUnderLineLabel, BorderLayout.SOUTH);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(lastPageButton, BorderLayout.WEST);
		panel.add(logoUnderLineLabel, BorderLayout.SOUTH);
//		panel.setBackground(Color.GRAY);
//		panel.setOpaque(false);
		return panel;
	}

	public void createEventPanel() throws SQLException, ParseException,SQLSyntaxErrorException {

		ResultSet result = conn.searchMyEvent();
		int count = 0;
		try {
			while (result.next()) {
				count++;
				ImageIcon logoIcon = new ImageIcon("logo.png");
				logoIcon.setImage(
						logoIcon.getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH));
				JLabel logoLabel = new JLabel();
				logoLabel.setIcon(logoIcon);
				String eventID = result.getString("Event_ID");
				JLabel eventIDLabel = new JLabel("    ID:" + eventID);
				JLabel titleLabel = new JLabel(result.getString("Title"));
				titleLabel.setFont(TItle_FONT);
				double score = conn.getAvg(eventID);
				JLabel timeAndLocationLabel = new JLabel("                     Time: " + result.getString("Time")
						+ " |  Location: " + result.getString("Location") + " |  Score: " + score);
				JButton seeReviewsButton = new JButton("Feedback");
				class CommentListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						seefeedbackpage seeFeedbackPage = new seefeedbackpage(conn, eventID);

					}
				}
				seeReviewsButton.addActionListener(new CommentListener());
				JButton deleteButton = new JButton("Delete");
				deleteButton.setSize(30, 10);
				class DeleteListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						conn.deleteEvent(eventID);
						dispose();
						try {
							holderpage holderPage = new holderpage(conn);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				deleteButton.addActionListener(new DeleteListener());
				JLabel underlineLabel = new JLabel(
						" -----------------------------------------------------------------------------------------------------------------------");
				JPanel westPanel = new JPanel();
				westPanel.add(logoLabel);
				JPanel centerPanel = new JPanel();
				centerPanel.add(titleLabel);
				JPanel eastPanel = new JPanel();
				eastPanel.setLayout(new BorderLayout());
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
				String eventTime = result.getString("Time");
				Date current = new Date();
				String currentTime = sdFormat.format(current);
				Date eventDate = sdFormat.parse(eventTime);
				Date currentDate = sdFormat.parse(currentTime);
				boolean notYet = eventDate.compareTo(currentDate) > 0;
				if (notYet) {
					eastPanel.add(deleteButton, BorderLayout.SOUTH);
				} else {
					eastPanel.add(seeReviewsButton, BorderLayout.SOUTH);
				}
				JPanel southPanel = new JPanel();
				southPanel.setLayout(new BorderLayout());
				southPanel.add(eventIDLabel, BorderLayout.WEST);
				southPanel.add(timeAndLocationLabel, BorderLayout.CENTER);
				southPanel.add(underlineLabel, BorderLayout.SOUTH);
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(westPanel, BorderLayout.WEST);
				panel.add(centerPanel, BorderLayout.CENTER);
				panel.add(eastPanel, BorderLayout.EAST);
				panel.add(southPanel, BorderLayout.SOUTH);
				this.panel.add(panel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count == 5) {
			int expansion = (count - 4) * 100;
			panel.setPreferredSize(new Dimension(0, 460 + expansion));
		} else if (count > 5) {
			int expansion = (count - 5) * 91;
			panel.setPreferredSize(new Dimension(0, 550 + expansion));
		} else {
			panel.setPreferredSize(new Dimension(0, 460));
		}
		//
		if (count == 0) {
			ImageIcon logoIcon = new ImageIcon("letgo.png");
			logoIcon.setImage(logoIcon.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
			JButton logoButton = new JButton();
			class CreateListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					createevent createEvent = new createevent(conn);
//					boolean result =createEvent.initialize();
					dispose();
				}
			}
			ActionListener createActionListener = new CreateListener();
			logoButton.addActionListener(createActionListener);
			logoButton.setIcon(logoIcon);
			logoButton.setBounds(105, 110, 300, 300);
			logoButton.setContentAreaFilled(false);
			logoButton.setBorderPainted(false);
			this.getContentPane().add(logoButton);
			JLabel remindLabel = new JLabel("Click me to creat your event!");
			remindLabel.setBounds(140, 390, 300, 20);
			remindLabel.setFont(REAIND_FONT);
			this.getContentPane().add(remindLabel);
		}
		//
	}
}

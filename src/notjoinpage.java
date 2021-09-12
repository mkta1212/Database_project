import java.awt.BorderLayout;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class notjoinpage extends JFrame {

	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton lastPageButton;
	private JLabel logoUnderLineLabel;
	private Connection_sql conn_sql;
	private static final Font TItle_FONT = new Font("activity", Font.BOLD, 20);
	private static final Font REMIND_FONT = new Font("activity", Font.BOLD, 18);
	private static final int ICON_WIDTH = 55;
	private static final int ICON_HEIGHT = 45;
	private static final int FRAME_WIDTH = 520;
	private static final int FRAME_HEIGHT = 510;
	// 資料庫帳密碼那些等你建好打你的上去 ,port & database記得改
	static final String DB_URL = "jdbc:mysql://localhost:3306/dbms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "27021797";

	public notjoinpage(Connection_sql conn_sql) throws ParseException {
		this.conn_sql = conn_sql;
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
		setTitle("Not yet");
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				myeventPage frame = new myeventPage(conn_sql);
				dispose();
			}
		}
		lastPageButton.addActionListener(new LastPageListener());
		this.logoUnderLineLabel = new JLabel(
				" ________________________________________________________________________");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(lastPageButton, BorderLayout.WEST);
		panel.add(logoUnderLineLabel, BorderLayout.SOUTH);
//		panel.setBackground(null);
//		panel.setOpaque(false);
		return panel;
	}

	public void createEventPanel() throws SQLException, ParseException {

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connection Success!");
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM event WHERE User_ID!=?");
		statement.setString(1, conn_sql.getUser().getID());
		int count = 0;
		// SQL到時候你再自己改，現在這個是方便我測試才這樣寫的
		try {
			ResultSet result = statement.executeQuery();

			try {
				while (result.next()) {
					count++;
					ImageIcon logoIcon = new ImageIcon("logo.png");
					logoIcon.setImage(logoIcon.getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT,
							java.awt.Image.SCALE_SMOOTH));
					JLabel logoLabel = new JLabel();
					logoLabel.setIcon(logoIcon);
					String eventID = result.getString("Event_ID");
					boolean checkJoin = conn_sql.checkJoin(eventID);
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
					String eventTime = result.getString("Time");
					Date current = new Date();
					String currentTime = sdFormat.format(current);
					Date eventDate = sdFormat.parse(eventTime);
					Date currentDate = sdFormat.parse(currentTime);
					if (checkJoin && eventDate.compareTo(currentDate) >= 0) {
						JLabel eventIDLabel = new JLabel("    ID:" + eventID);
						JLabel titleLabel = new JLabel(result.getString("Title"));
						titleLabel.setFont(TItle_FONT);
						JLabel timeAndLocationLabel = new JLabel("                     Time: "
								+ result.getString("Time") + " |  Location: " + result.getString("Location"));
						String content = result.getString("Content");
						// Button的功能我沒做，交給你連接frame了
						JButton joinButton = new JButton("Cancel");
						class JoinListener implements ActionListener {
							public void actionPerformed(ActionEvent e) {
								conn_sql.cancelEvent(eventID);
								dispose();
								try {
									notjoinpage notJoinPage = new notjoinpage(conn_sql);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						joinButton.addActionListener(new JoinListener());
						JButton detailButton = new JButton("Detail");
						class DetailListener implements ActionListener {
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(null, content);
							}
						}
						detailButton.addActionListener(new DetailListener());
						JLabel underlineLabel = new JLabel(
								" -----------------------------------------------------------------------------------------------------------------------");
						JPanel westPanel = new JPanel();
						westPanel.add(logoLabel);
						JPanel centerPanel = new JPanel();
						centerPanel.add(titleLabel);
						JPanel eastPanel = new JPanel();
						eastPanel.setLayout(new BorderLayout());
						eastPanel.add(joinButton, BorderLayout.NORTH);
						eastPanel.add(detailButton, BorderLayout.SOUTH);
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
					} else {

					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 這個調整panel大小目前應該都OK，但可能太少或是太多可能會有點跑掉，如果你有測試到的話我再改
			if (count == 5) {
				int expansion = (count - 4) * 100;
				panel.setPreferredSize(new Dimension(0, 460 + expansion));
			} else if (count > 5) {
				int expansion = (count - 5) * 92;
				panel.setPreferredSize(new Dimension(0, 550 + expansion));
			} else {
				panel.setPreferredSize(new Dimension(0, 460));
			}
		} catch (SQLException e) {

		}
		if (count == 0) {
			ImageIcon logoIcon = new ImageIcon("letgo.png");
			logoIcon.setImage(logoIcon.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
			JButton logoButton = new JButton();
			class HomeListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					try {
						Homepage frame = new Homepage(conn_sql);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					boolean result =createEvent.initialize();
					dispose();
				}
			}
			ActionListener createActionListener = new HomeListener();
			logoButton.addActionListener(createActionListener);
			logoButton.setIcon(logoIcon);
			logoButton.setBounds(105, 110, 300, 300);
			logoButton.setContentAreaFilled(false);
			logoButton.setBorderPainted(false);
			this.getContentPane().add(logoButton);
			JLabel remindLabel = new JLabel("Click me to join events!");
			remindLabel.setBounds(140, 390, 300, 20);
			remindLabel.setFont(REMIND_FONT);
			this.getContentPane().add(remindLabel);
		}
		statement.close();
		conn.close();
	}

	/**
	 * Launch the application.
	 */

}

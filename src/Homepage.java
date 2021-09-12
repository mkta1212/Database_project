import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
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

import javax.swing.*;

public class Homepage extends JFrame {
	private JMenuBar functionMenubar;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel logoLabel;
	private JLabel logoUnderLineLabel;
	private Connection_sql conn_sql;
	private static final Font LOGO_FONT = new Font("activity", Font.BOLD + Font.ITALIC, 28);
	private static final Font TItle_FONT = new Font("activity", Font.BOLD, 20);
	private static final int ICON_WIDTH = 55;
	private static final int ICON_HEIGHT = 45;
	private static final int FRAME_WIDTH = 520;
	private static final int FRAME_HEIGHT = 510;
	static final String DB_URL = "jdbc:mysql://localhost:3306/dbms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "27021797";

	public Homepage(Connection_sql conn) throws ParseException {

		this.conn_sql = conn;
		this.panel = new JPanel();
		this.scrollPane = new JScrollPane(panel);
		panel.add(createTitlePanel());
		try {
			createEventPanel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(scrollPane);
		setVisible(true);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Home Page");
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
		this.functionMenubar = new JMenuBar();
		// Not yet set function
		JMenu menu = new JMenu("Function List");
		JMenuItem createListener = new JMenuItem("Create event");
		class CreateListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				createevent createEvent = new createevent(conn_sql);
//				boolean result =createEvent.initialize();
				dispose();
			}
		}
		createListener.addActionListener(new CreateListener());

		JMenuItem historyListener = new JMenuItem("Your event");
		class HistoryListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				myeventPage myEventPage = new myeventPage(conn_sql);
				dispose();
			}
		}
		historyListener.addActionListener(new HistoryListener());

		JMenuItem logoutListener = new JMenuItem("Log out");
		class LogoutListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				dispose();
			}
		}
		logoutListener.addActionListener(new LogoutListener());

		menu.add(createListener);
		menu.add(historyListener);
		menu.add(logoutListener);
		functionMenubar.add(menu);
		this.logoLabel = new JLabel("揪團去");
		logoLabel.setFont(LOGO_FONT);
		logoLabel.setOpaque(false);
		this.logoUnderLineLabel = new JLabel(
				" ________________________________________________________________________");
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(functionMenubar);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(logoLabel, BorderLayout.WEST);
		centerPanel.add(logoUnderLineLabel, BorderLayout.SOUTH);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		return panel;
	}

	public void createEventPanel() throws SQLException, ParseException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connection Success!");
//		PreparedStatement statement = conn.prepareStatement("SELECT * FROM dbms.event WHERE User_ID!=? AND Event_ID!=(SELECT Event_ID FROM dbms.Join WHERE User_ID=?)");
//		statement.setString(1,conn_sql.getUser().getID());
//		statement.setString(2,conn_sql.getUser().getID());
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM event WHERE User_ID!=?");
		statement.setString(1, conn_sql.getUser().getID());

		// SQL到時候你再自己改，現在這個是方便我測試才這樣寫的
		try {
			ResultSet result = statement.executeQuery();
			int count = 0;

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
					boolean notYet = eventDate.compareTo(currentDate) > 0;
					if (checkJoin) {

					} else if (notYet) {
						JLabel eventIDLabel = new JLabel("    ID:" + eventID);
						JLabel titleLabel = new JLabel(result.getString("Title"));
						titleLabel.setFont(TItle_FONT);
						JLabel timeAndLocationLabel = new JLabel("                     Time: "
								+ result.getString("Time") + " |  Location: " + result.getString("Location"));
						String content = result.getString("Content");
						// Button的功能我沒做，交給你連接frame了
						JButton joinButton = new JButton("Join");
						class JoinListener implements ActionListener {
							public void actionPerformed(ActionEvent e) {
								conn_sql.joinEvent(eventID);
								dispose();
								try {
									Homepage homePage = new Homepage(conn_sql);
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
			//
		} catch (SQLException e) {
		}
		statement.close();
		conn.close();
	}

//	public static void main(String[] args) {
//		JFrame frame = new Homepage();
//		try {
//			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			System.out.println("Connection Success!");
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

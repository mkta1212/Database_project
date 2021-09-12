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
import javax.swing.*;

public class seefeedbackpage extends JFrame {
	private JMenuBar functionMenubar;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel logoLabel;
	private JLabel logoUnderLineLabel;
	private Connection_sql conn_sql;
	private String eventID;
	private static final Font LOGO_FONT = new Font("activity", Font.BOLD + Font.ITALIC, 28);
	private static final Font TItle_FONT = new Font("activity", Font.BOLD, 20);
	private static final int ICON_WIDTH = 55;
	private static final int ICON_HEIGHT = 45;
	private static final int FRAME_WIDTH = 520;
	private static final int FRAME_HEIGHT = 510;
	static final String DB_URL = "jdbc:mysql://localhost:3306/dbms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "27021797";

	public seefeedbackpage(Connection_sql conn, String eventID) {
		this.conn_sql = conn;
		this.eventID = eventID;
		this.panel = new JPanel();
		this.scrollPane = new JScrollPane(panel);
//		panel.add(createTitlePanel());
		try {
			createEventPanel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JButton backBtn = new JButton("←");
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}
		backBtn.addActionListener(new backListener());
		backBtn.setBounds(0, 0, 45, 23);
		add(backBtn);
		add(scrollPane);
		setVisible(true);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Home Page");
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

		JMenuItem historyListener = new JMenuItem("My event");
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
		this.logoLabel = new JLabel("���");
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

	public void createEventPanel() throws SQLException {
		try {

			ResultSet result = conn_sql.seeFeedback(eventID);

			int count = 0;
			try {
				while (result.next()) {

					count++;
					ImageIcon logoIcon = new ImageIcon("logo.png");
					logoIcon.setImage(logoIcon.getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT,
							java.awt.Image.SCALE_SMOOTH));
					JLabel logoLabel = new JLabel();
					logoLabel.setIcon(logoIcon);
//					String eventID=result.getString("Event_ID");
//					JLabel eventIDLabel = new JLabel("    ID:" + eventID);
					JLabel contentLabel = new JLabel(result.getString("Feedback"));
					contentLabel.setFont(TItle_FONT);
					JLabel scoreLabel = new JLabel("                     Score: " + result.getString("Score"));
					JLabel underlineLabel = new JLabel(
							" -----------------------------------------------------------------------------------------------------------------------");
					JPanel westPanel = new JPanel();
					westPanel.add(logoLabel);
					JPanel centerPanel = new JPanel();
					centerPanel.add(contentLabel);
					JPanel southPanel = new JPanel();
					southPanel.setLayout(new BorderLayout());
					southPanel.add(scoreLabel, BorderLayout.CENTER);
//					southPanel.add(eventIDLabel, BorderLayout.WEST);
					southPanel.add(underlineLabel, BorderLayout.SOUTH);
					JPanel panel = new JPanel();
					panel.setLayout(new BorderLayout());
					panel.add(westPanel, BorderLayout.WEST);
					panel.add(centerPanel, BorderLayout.CENTER);
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
				int expansion = (count - 5) * 92;
				panel.setPreferredSize(new Dimension(0, 550 + expansion));
			} else {
				panel.setPreferredSize(new Dimension(0, 460));
			}
		} catch (Exception e) {

		}
	}
}

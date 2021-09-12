import java.awt.BorderLayout;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ManageEventPage extends JFrame {

	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton lastPageButton;
	private JLabel logoUnderLineLabel;
	private Connection_sql conn_sql;
	private static final Font TItle_FONT = new Font("activity", Font.BOLD, 20);
	private static final int ICON_WIDTH = 55;
	private static final int ICON_HEIGHT = 45;
	private static final int FRAME_WIDTH = 520;
	private static final int FRAME_HEIGHT = 510;
	static final String DB_URL = "jdbc:mysql://localhost:3306/dbms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "27021797";
	//

	public ManageEventPage(Connection_sql conn_sql) throws ParseException {
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
		setLocation(200, 100);
		setTitle("Manage Events");
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
		this.lastPageButton = new JButton("上一頁");
		class LastPageListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				managerpage managerPage = new managerpage(conn_sql);
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
//		panel.setBackground(null);
//		panel.setOpaque(false);
		return panel;
	}

	public void createEventPanel() throws SQLException, ParseException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM event");
		ResultSet result = statement.executeQuery();
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
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
				String eventTime = result.getString("Time");
				Date current = new Date();
				String currentTime = sdFormat.format(current);
				Date eventDate = sdFormat.parse(eventTime);
				Date currentDate = sdFormat.parse(currentTime);
				boolean notYet = eventDate.compareTo(currentDate) > 0;
				if (notYet) {
					JLabel eventIDLabel = new JLabel("    ID:" + eventID);
					JLabel titleLabel = new JLabel(result.getString("Title"));
					titleLabel.setFont(TItle_FONT);
					JLabel timeAndLocationLabel = new JLabel("                     Time: " + result.getString("Time")
							+ " |  Location: " + result.getString("Location"));
					String content = result.getString("Content");
					// Button��������漱蝯虫����frame鈭�
					JButton joinButton = new JButton("Delete");
					class JoinListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							conn_sql.deleteEvent(eventID);
							dispose();
							try {
								ManageEventPage managenEventtPage = new ManageEventPage(conn_sql);
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
		if (count == 5) {
			int expansion = (count - 4) * 100;
			panel.setPreferredSize(new Dimension(0, 460 + expansion));
		} else if (count > 5) {
			int expansion = (count - 5) * 91;
			panel.setPreferredSize(new Dimension(0, 550 + expansion));
		} else {
			panel.setPreferredSize(new Dimension(0, 460));
		}
	}
}
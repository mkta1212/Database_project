import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class blockuserpage {

	private JFrame frame;
	private JTextField txtUserId;
	private User searchUser;
	private JTextArea textArea;
	private double avgScore;
	private Connection_sql conn_sql;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					blockuserpage window = new blockuserpage();
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
	public blockuserpage(Connection_sql conn_sql) {
		this.conn_sql=conn_sql;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Block Page");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImagePanel bgImagePanel = new ImagePanel("background2.png");
		bgImagePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(bgImagePanel);
		frame.getContentPane().setLayout(null);
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("mouse.png");
		Cursor cu = tk.createCustomCursor(img,new Point(16,16),"stick");
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
		
		txtUserId = new JTextField();
//		txtUserId.setText("user ID");
		txtUserId.setBounds(64, 44, 130, 26);
		frame.getContentPane().add(txtUserId);
		txtUserId.setColumns(10);
		JButton blockBtn = new JButton("Block");
		JButton searchButton = new JButton("Search");
		class SearchListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				searchUser=conn_sql.getSearchUser(txtUserId.getText());
				avgScore=conn_sql.getUserAvg(txtUserId.getText());
				if(searchUser!=null)
				{
					String idStr="ID: "+searchUser.getID();
					String nameStr="Name: "+searchUser.getName();
					String emailStr="Email: "+searchUser.getEmail();
					String avgStr="AVG: "+avgScore;
					String validity="Validity: "+searchUser.getValidity();
					textArea.setText(idStr+"\n"+nameStr+"\n"+emailStr+"\n"+avgStr+"\n"+validity);
					if(searchUser.getValidity()==1)
					{
						blockBtn.setVisible(true);
					}
					else
					{
						blockBtn.setVisible(false);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Unknown user");
				}
			}
		}
		searchButton.addActionListener(new SearchListener());
		searchButton.setBounds(243, 44, 117, 29);
		//
		searchButton.setBorder(BorderFactory.createLoweredBevelBorder());
		searchButton.setContentAreaFilled(false);
		searchButton.setFont(new Font("button", Font.BOLD, 15));
		searchButton.setForeground(Color.WHITE);
		searchButton.addMouseListener(new MouseListener() {

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
				searchButton.setFont(new Font("button", Font.BOLD, 15));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
//				holderBtn.setBackground(Color.BLACK);
//				holderBtn.setContentAreaFilled(true);
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
				searchButton.setFont(new Font("mouseListener", Font.BOLD, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				holderBtn.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		//
		frame.getContentPane().add(searchButton);
		class BlockListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				conn_sql.blockUser(txtUserId.getText());
				textArea.setText("");
				txtUserId.setText("");
			}
		}
		blockBtn.addActionListener(new BlockListener());
		blockBtn.setBounds(164, 212, 117, 29);
		blockBtn.setVisible(false);
		blockBtn.setBorder(BorderFactory.createLoweredBevelBorder());
		blockBtn.setContentAreaFilled(false);
		blockBtn.setFont(new Font("button", Font.BOLD, 15));
		blockBtn.setForeground(Color.WHITE);
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
		frame.getContentPane().add(blockBtn);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(74, 94, 279, 93);
		frame.getContentPane().add(textArea);
		JButton backBtn = new JButton("");
		class backListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				managerpage managerPage=new managerpage(conn_sql);
				
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
		frame.getContentPane().add(backBtn);
//		JLabel lblNewLabel = new JLabel("                                  Result");
//		lblNewLabel.setBounds(54, 95, 87, 26);
//		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
		frame.getRootPane().setDefaultButton(searchButton);
		
	}

}

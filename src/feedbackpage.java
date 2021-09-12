import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JComboBox;

public class feedbackpage extends JFrame {

	private JTextArea textArea;
	Connection_sql conn;
	private String eventID;
	private String eventTitle;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					feedbackpage window = new feedbackpage();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public feedbackpage(Connection_sql conn,String eventID,String eventTitle) {
		this.conn=conn;
		this.eventID=eventID;
		this.eventTitle=eventTitle;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("mouse.png");
		Cursor cu = tk.createCustomCursor(img,new Point(16,16),"stick");
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
		//
		
		textArea = new JTextArea();
		textArea.setBounds(57, 49, 295, 144);
//		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Comment");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(57, 10, 92, 29);
		getContentPane().add(lblNewLabel);
		
//		JScrollPane scrollPane = new JScrollPane(textArea);
//		scrollPane.setBounds(349, 51, 15, 173);
//		scrollPane.setLocation(349, 51);
//		JPanel panel=new JPanel();
//		panel.add(scrollPane);
		getContentPane().add(textArea);
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		for(int i=1;i<=5;i++)
		{
			comboBox.addItem(i);
		}
		comboBox.setBounds(76, 216, 59, 23);
		getContentPane().add(comboBox);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setBounds(235, 213, 117, 29);
		class SubmitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				conn.writeComment(eventID,(int)comboBox.getSelectedItem(),textArea.getText());
				dispose();
			}
		}
		submitBtn.addActionListener(new SubmitListener());
		getContentPane().add(submitBtn);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		
	}
}

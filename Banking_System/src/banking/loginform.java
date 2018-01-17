package banking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginform {
	JFrame jf;
	JPanel jp;
	JTextField accnotf;
	JPasswordField ppf;
	JLabel ulbl, plbl, glbl, piclbl;
	String aid;
	ImageIcon img ;
	Font ff = new Font("times new roman", Font.BOLD, 30);

	loginform() {
		jf = new JFrame("LOGIN");
		jp = new JPanel();
		accnotf = new JTextField();
		ppf = new JPasswordField();
		ulbl = new JLabel("ACCOUNT NO.");
		plbl = new JLabel("PASSWORD");
		jp.setLayout(null);
		jp.setBackground(Color.green);
		
/*		PicPanel mainPanel = new PicPanel("E:/workspace/Banking_System/passk/Free-Rojgar-Samachar-Alert-IDBI-Bank-Logo-150x150.gif");
	    mainPanel.setBounds(190,100,150,150);
	    jp.add(mainPanel);*/
		
		img = new ImageIcon("E:/Educational softwares and project/workspace/Banking_System/pics/Free-Rojgar-Samachar-Alert-IDBI-Bank-Logo-150x150.gif");
	
		piclbl = new JLabel(img);
		piclbl.setBounds(190, 100, 150, 150);
		jp.add(piclbl);
		
		
		ulbl.setBounds(20, 260, 80, 30);
		jp.add(ulbl);

		plbl.setBounds(20, 290, 80, 30);
		jp.add(plbl);

		accnotf.setBounds(140, 265, 120, 20);
		jp.add(accnotf);

		ppf.setBounds(140, 295, 120, 20);
		ppf.addKeyListener(new keyEvent());
		jp.add(ppf);

		JButton logbtn = new JButton("LOGIN");
		logbtn.setBounds(170, 330, 100, 30);
		logbtn.addActionListener(new logbt());
		jp.add(logbtn);

		JButton rbtn = new JButton("RESET");
		rbtn.setBounds(30, 330, 100, 30);
		rbtn.addActionListener(new rbt());
		jp.add(rbtn);

		JButton adminbtn = new JButton("ADMIN");
		adminbtn.setBounds(430, 330, 100, 30);
		adminbtn.addActionListener(new adminbt());
		jp.add(adminbtn);

		JButton forgetbtn = new JButton("FORGET PASSWORD");
		forgetbtn.setBounds(70, 390, 160, 30);
		forgetbtn.addActionListener(new forgetbt());
		jp.add(forgetbtn);

		glbl = new JLabel("WELCOME TO THE LOGIN PANEL");
		glbl.setBounds(10, 10, 550, 80);
		glbl.setFont(ff);
		jp.add(glbl);

		jf.add(jp);
		jf.setVisible(true);
		jf.setSize(550, 490);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	class keyEvent implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_ENTER)
			{
				int flag = 0;
				String accno = accnotf.getText();
				String pass = ppf.getText();
				aid = accno ;
				Connection con = DataBaseConnection.javaConnection();
				try {
					Statement stmt = con.createStatement();
					String query = "select * from accountinfo where accno = '" + accno + "' && password = '" + pass + "' ;";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						String account, password;
						account = rs.getString("accno");
						password = rs.getString("Password");
						String name = rs.getString("name");

						if (accno.equals(account) && pass.equals(password)) {

							JOptionPane.showMessageDialog(null, "Access Granted");
							jf.dispose();
							new UserWelcome(name, rs.getString("accno"));
						}
					}
				} catch (SQLException a) {
					a.printStackTrace();
				}
 			}
	
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	class rbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			accnotf.setText("");
			ppf.setText("");
		}
	}

	class adminbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			jf.dispose();
			new Adminlogin();
		}
	}

	class logbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int flag = 0;
			String accno = accnotf.getText();
			String pass = ppf.getText();
			aid = accno ;
			Connection con = DataBaseConnection.javaConnection();
			try {
				Statement stmt = con.createStatement();
				String query = "select * from accountinfo where accno = '" + accno + "' && password = '" + pass + "' ;";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String account, password;
					account = rs.getString("accno");
					password = rs.getString("Password");
					String name = rs.getString("name");

					if (accno.equals(account) && pass.equals(password)) {

						JOptionPane.showMessageDialog(null, "Access Granted");
						jf.dispose();
						new UserWelcome(name, rs.getString("accno"));
					}
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
	}
	class forgetbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			aid= accnotf.getText();
			jf.dispose();
			new forgetpass(aid);
		}
	}
	class PicPanel extends JPanel{

	    private BufferedImage image;
	    private int w,h;
	    public PicPanel(String fname){

	        //reads the image
	        try {
	            image = ImageIO.read(new File(fname));
	            w = image.getWidth();
	            h = image.getHeight();

	        } catch (IOException ioe) {
	            System.out.println("Could not read in the pic");
	            //System.exit(0);
	        }

	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(w,h);
	    }
	    //this will draw the image
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        g.drawImage(image,0,0,this);
	    }
	}
}

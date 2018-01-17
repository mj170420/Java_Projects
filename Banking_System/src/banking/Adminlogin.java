package banking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class Adminlogin {

	JFrame jf;
	JPanel jp;
	JTextField pnotf;
	JPasswordField ppf;
	JLabel ulbl, plbl, glbl;
	Font ff = new Font("times new roman", Font.BOLD, 30);

	Adminlogin() {
		jf = new JFrame("LOGIN");
		jp = new JPanel();
		pnotf = new JTextField();
		ppf = new JPasswordField();
		ulbl = new JLabel("POST ID");
		plbl = new JLabel("PASSWORD");
		jp.setLayout(null);
		jp.setBackground(Color.green);
		
		
	    ImageIcon img = new ImageIcon("E:/Educational softwares and project/workspace/Banking_System/pics/Free-Rojgar-Samachar-Alert-IDBI-Bank-Logo-150x150.gif");
	    
	    JLabel jl = new JLabel(img);
	    jl.setBounds(400, 100, 150, 150);
	    jp.add(jl);
	    
		ulbl.setBounds(20, 90, 80, 30);
		jp.add(ulbl);

		plbl.setBounds(20, 120, 80, 30);
		jp.add(plbl);

		pnotf.setBounds(140, 95, 120, 20);
		jp.add(pnotf);

		ppf.setBounds(140, 125, 120, 20);
		ppf.addKeyListener(new KeyEvent());
		jp.add(ppf);

		JButton logbtn = new JButton("ADMIN LOGIN");
		logbtn.setBounds(170, 160, 150, 30);
		logbtn.addActionListener(new logbt());
		jp.add(logbtn);

		JButton rbtn = new JButton("RESET");
		rbtn.setBounds(30, 160, 100, 30);
		rbtn.addActionListener(new rbt());
		jp.add(rbtn);

		JButton forgetbtn = new JButton("FORGET PASSWORD");
		forgetbtn.setBounds(70, 220, 160, 30);
		forgetbtn.addActionListener(new forgetbt());
		jp.add(forgetbtn);

		glbl = new JLabel("WELCOME TO THE ADMIN LOGIN PANEL");
		glbl.setBounds(10, 10, 650, 80);
		glbl.setFont(ff);
		jp.add(glbl);

		jf.add(jp);
		jf.setVisible(true);
		jf.setSize(650, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
	
	class KeyEvent implements KeyListener
	{

		public void keyPressed(java.awt.event.KeyEvent e) {
			if(e.getKeyCode()==e.VK_ENTER)
			{
				int flag = 0;
				String pno = pnotf.getText();
				String pass = ppf.getText();
				Connection con = DataBaseConnection.javaConnection();
				try {
					Statement stmt = con.createStatement();
					String query = "select * from adminlogs where postid = '" + pno + "' && password = '" + pass + "'";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						String postid, password;
						postid = rs.getString("postid");
						password = rs.getString("password");
						String name = rs.getString("Name");

						if (pno.equals(postid) && pass.equals(password)) {

							JOptionPane.showMessageDialog(null, "Access Granted");
							jf.dispose();
							new Admin(name, pno);
						}
					}
				} catch (SQLException a) {
					a.printStackTrace();
				}	
			}
		}

		@Override
		public void keyReleased(java.awt.event.KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(java.awt.event.KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	class rbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pnotf.setText("");
			ppf.setText("");
		}
	}

	class forgetbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Contact DataBase Administrator For Help !!!");
			
		}
	}

	class logbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int flag = 0;
			String pno = pnotf.getText();
			String pass = ppf.getText();
			Connection con = DataBaseConnection.javaConnection();
			try {
				Statement stmt = con.createStatement();
				String query = "select * from adminlogs where postid = '" + pno + "' && password = '" + pass + "'";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String postid, password;
					postid = rs.getString("postid");
					password = rs.getString("password");
					String name = rs.getString("Name");

					if (pno.equals(postid) && pass.equals(password)) {

						JOptionPane.showMessageDialog(null, "Access Granted");
						jf.dispose();
						new Admin(name, pno);
					}
				}
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
	}
}

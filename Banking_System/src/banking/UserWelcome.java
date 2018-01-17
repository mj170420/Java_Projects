package banking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserWelcome {
	JFrame jf;
	JPanel jp;
	
	JLabel ulbl, slbl;

	JButton bal, with, depo, trans, mini, loan, logout, submit, chkstt;

	JTextArea sta;

	Font ff = new Font("times new roman", Font.BOLD, 15);

	static int loanid = 131611 ;
/*	int n ;*/
	
	String name, aid;
	int Loanno ;
	UserWelcome(String name, String aid) {
		jf = new JFrame("Welcome");
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.yellow);
		
		this.name = name;
		this.aid = aid;

		ulbl = new JLabel("Hello Mr." + name + " welcome to this application ");
		ulbl.setBounds(80, 10, 400, 25);
		ulbl.setFont(ff);
		jp.add(ulbl);
		
		ImageIcon icon = new ImageIcon("E:/Educational softwares and project/workspace/Banking_System/pics/Free-Rojgar-Samachar-Alert-IDBI-Bank-Logo-150x150.gif", "my logo");
		JLabel label = new JLabel(icon);
		label.setBounds(80, 320, 300, 300);
		label.setToolTipText("IDBI Corporation Limited");
		label.addMouseListener(new click());
		jp.add(label);
		
		bal = new JButton("Balance Enquiry");
		bal.setBounds(10, 60, 190, 25);
		bal.addActionListener(new balance());
		bal.setToolTipText("Check Your Account Balance");
		bal.setFont(ff);
		jp.add(bal);

		with = new JButton("Withdrawal");
		with.setBounds(250, 60, 190, 25);
		with.addActionListener(new withdrawal());
		with .setToolTipText("Withdraw Amount From Account");
		with.setFont(ff);
		jp.add(with);

		depo = new JButton("Deposit");
		depo.setBounds(10, 100, 190, 25);
		depo.addActionListener(new deposit());
		depo.setToolTipText("Deposit Amount to Your Account");
		depo.setFont(ff);
		jp.add(depo);

		trans = new JButton("Balance Transfer");
		trans.setBounds(250, 100, 190, 25);
		trans.addActionListener(new transfer());
		trans.setToolTipText("For Balance Transfer");
		trans.setFont(ff);
		jp.add(trans);

		mini = new JButton("Mini Statements");
		mini.setBounds(10, 140, 190, 25);
		mini.addActionListener(new statement());
		mini.setToolTipText("Mini Statement For Your Account");
		mini.setFont(ff);
		jp.add(mini);

		loan = new JButton("Request For Loan");
		loan.setBounds(10, 180, 190, 25);
		loan.addActionListener(new loanbt());
		loan.setToolTipText("Apply For Loan");
		loan.setFont(ff);
		jp.add(loan);
		
		chkstt=new JButton("CHECK STATUS");
		chkstt.setBounds(250, 140, 190, 25);
		chkstt.addActionListener(new chkstt());
		chkstt.setToolTipText("Check Your Loan Status");
		chkstt.setFont(ff);
		jp.add(chkstt);
		
		logout = new JButton("LOGOUT");
		logout.setBounds(250, 180, 190, 25);
		logout.addActionListener(new log());
		logout .setToolTipText("Logout Of your Account");
		logout.setFont(ff);
		jp.add(logout);

		slbl = new JLabel("Submit Your Feedback");
		slbl.setBounds(10, 240, 190, 30);
		slbl.setFont(ff);
		jp.add(slbl);

		sta = new JTextArea();
		sta.setBounds(10, 270, 350, 100);
		sta.setLineWrap(true);
		jp.add(sta);

		submit = new JButton("Submit");
		submit.setBounds(380, 300, 100, 25);
		submit.addActionListener(new submitbtn());
		submit.setToolTipText("Submit your Feedback");
		submit.setFont(ff);
		jp.add(submit);

		jf.add(jp);
		jf.setVisible(true);
		jf.setSize(500, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class click implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JOptionPane.showMessageDialog(null, "Redirecting to Profile Page");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {/*
			JOptionPane.showMessageDialog(null, "This is Home Location Of IDBI Server, Thank You!!");*/
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class chkstt implements ActionListener
	{

	public void actionPerformed(ActionEvent arg0) {
			jf.dispose();
			new loanstat(aid , name);
		}
		
	}
	class loanbt implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String loanamt = JOptionPane.showInputDialog(null, "Enter Amount For Loan");
			String loantime = JOptionPane.showInputDialog(null, "Enter Time For Loan");
			String loanrate = JOptionPane.showInputDialog(null, "Enter Rate of Interest For Loan");
			int n = JOptionPane.showConfirmDialog(null, "Are you confirm to send loan request ?");
			Connection con = DataBaseConnection.javaConnection();
			/*try {
				Statement stmt = con.createStatement();
				String query = "Select count(*) from loanreq";
				ResultSet rs = stmt.executeQuery(query);
				n = rs.getInt(1);
	  			loanid = loanid + n ;
				Loanno = loanid++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			*/
			if(n==JOptionPane.YES_OPTION)
			{
				try {
					Statement stt= con.createStatement();
					String query = "insert into loanreq(Isaccno, Issuingamt, timealloted, rateofinterest, status) values('"+aid+"', "+loanamt+", '"+loantime+"', '"+loanrate+"', 'Pending' )";
					int res = stt.executeUpdate(query);
					if(res>0)
					{
						JOptionPane.showMessageDialog(null, "Sucessfully Submitted !!!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	class balance implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Connection cnt = DataBaseConnection.javaConnection();
			try {
				Statement stt = cnt.createStatement();
				String qq = "select balance from accountinfo where accno='" + aid + "'";
				ResultSet rr = stt.executeQuery(qq);
				if (rr.next()) {
					JOptionPane.showMessageDialog(null, "your balance is  " + rr.getString("balance"));
				} else {
					JOptionPane.showMessageDialog(null, "some error occured");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class withdrawal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String amt = JOptionPane.showInputDialog(null, "Pls enter amount you want to withdraw");
			int amnt = Integer.parseInt(amt);
			Connection cnt = DataBaseConnection.javaConnection();
			try {
				Statement stt = cnt.createStatement();
				String qq = "select balance from accountinfo where accno='" + aid + "'";
				ResultSet rr = stt.executeQuery(qq);
				if (rr.next()) {
					if (Integer.parseInt(rr.getString("balance")) > amnt) {
						String qq1 = "update accountinfo set balance=balance-" + amnt + " where accno='" + aid + "'";
						int rr1 = stt.executeUpdate(qq1);
						if (rr1 > 0) {
							Date d = new Date();
							String inq = "insert into transaction(tdate, accno, balance, ttype) values ('" + d + "','"
									+ aid + "','" + amt + "','withdraw')";
							stt.executeUpdate(inq);
							JOptionPane.showMessageDialog(null, "your balance is withdraw successfully");
						} else {
							JOptionPane.showMessageDialog(null, "some error occured in updation");
						}
					} else {
						JOptionPane.showMessageDialog(null, "you dont have sufficient balance");
					}

				} else {
					JOptionPane.showMessageDialog(null, "some error occured");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class deposit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String amt = JOptionPane.showInputDialog(null, "Pls enter amount you want to deposit");
			Connection cnt = DataBaseConnection.javaConnection();
			try {
				Statement stt = cnt.createStatement();
				String qq = "update accountinfo set balance=balance+" + amt + " where accno='" + aid + "'";
				int rr = stt.executeUpdate(qq);
				if (rr >= 0) {
					Date d = new Date();
					String inq = "insert into transaction(tdate, accno, balance, ttype) values ('" + d + "','" + aid
							+ "','" + amt + "','deposit')";
					stt.executeUpdate(inq);
					JOptionPane.showMessageDialog(null, "your balance is deposited successfully");
				} else {
					JOptionPane.showMessageDialog(null, "some error occured");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	class transfer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "FORWARDNG TO CASH TRANSFER DESK");
			jf.dispose();
			new transferctr(name, aid);
		}
	}

	class statement implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "TRANSFERING THE TRANSACTION DATA FROM SERVER");
			JOptionPane.showMessageDialog(null, "MINI STATEMENT:-");
			 ArrayList<transactions> elist= new ArrayList<transactions>();
				Connection cnt= DataBaseConnection.javaConnection();
				try {
					Statement stt= cnt.createStatement();
					String qq="select * from transaction where accno='"+aid+"'";
					ResultSet rr= stt.executeQuery(qq);
					while(rr.next())
					{
						transactions err= new transactions();
						err.setIdt(rr.getString("idt"));
						err.setAccno(rr.getString("accno"));
						err.setTdate(rr.getString("tdate"));
						err.setTtype(rr.getString("ttype"));
						err.setBalance(rr.getString("balance"));
						elist.add(err);
					}
					new minitable(elist);
					jf.dispose();
		}
				catch(SQLException er)
				{
					er.printStackTrace();
				}
	}
	}

	class log implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Thanks for Visit......\n Have a Nice Day");
			jf.dispose();
			new loginform();
		}
	}

	class submitbtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "THANX FOR YOUR VALUABLE FEEDBACK");
			sta.setText("");
		}
	}
}

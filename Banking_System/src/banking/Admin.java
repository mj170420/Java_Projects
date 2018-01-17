package banking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Admin {
	JFrame jf;
	JPanel jp;

	JLabel ulbl, slbl;

	JButton bal, with, depo, trans, mini, newacc, submit, editinfo, loan;

	JTextArea sta;

	Font ff = new Font("times new roman", Font.BOLD, 15);

	String name, pid;

	Admin(String name, String pid) {
		jf = new JFrame("Welcome");
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.yellow);

		this.name = name;
		this.pid = pid;

		ulbl = new JLabel("Hello Mr." + name + " welcome to Portal ");
		ulbl.setBounds(120, 10, 500, 25);
		ulbl.setFont(ff);
		jp.add(ulbl);

		bal = new JButton("Balance Enquiry");
		bal.setBounds(10, 60, 190, 25);
		bal.addActionListener(new balance());
		bal.setFont(ff);
		jp.add(bal);

		with = new JButton("Withdrawal");
		with.setBounds(250, 60, 190, 25);
		with.addActionListener(new withdrawalbtn());
		with.setFont(ff);
		jp.add(with);

		depo = new JButton("Deposit");
		depo.setBounds(10, 100, 190, 25);
		depo.addActionListener(new depositbtn());
		depo.setFont(ff);
		jp.add(depo);

		trans = new JButton("Balance Transfer");
		trans.setBounds(250, 100, 190, 25);
		trans.addActionListener(new transfer());
		trans.setFont(ff);
		jp.add(trans);

		mini = new JButton("CHANGE PASSWORD");
		mini.setBounds(10, 140, 190, 25);
		mini.addActionListener(new statement());
		mini.setFont(ff);
		jp.add(mini);

		editinfo = new JButton("EDIT ACCOUNT");
		editinfo.setBounds(10, 190, 190, 25);
		editinfo.addActionListener(new edit());
		editinfo.setFont(ff);
		jp.add(editinfo);
		
		newacc = new JButton("NEW ACCOUNT");
		newacc.setBounds(250, 140, 190, 25);
		newacc.addActionListener(new log());
		newacc.setFont(ff);
		jp.add(newacc);
		
		loan = new JButton("LOAN WINDOW");
		loan.setBounds(250, 190, 190, 25);
		loan.addActionListener(new loanwind());
		loan.setFont(ff);
		jp.add(loan);
		
		
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
		submit.setFont(ff);
		jp.add(submit);
		
		jf.add(jp);
		jf.setVisible(true);
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class loanwind implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
	jf.dispose();
	new loan_window(pid , name);
			
		}
		
	}

	class balance implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String aid=JOptionPane.showInputDialog(null, "Enter the Account Number For Enquiry");
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

	class withdrawalbtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Launching Withdrawal Form");
			jf.dispose();
			String name = JOptionPane.showInputDialog(null,"Enter Account Number Of Sender"); 
			new withdrawal(name, pid);
		}
	}

	class depositbtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "FORWARDING TO CASH DEPOSIT COUNTER");
			jf.dispose();
			new deposit(name , pid);
		}
	}

	class transfer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "FORWARDNG TO CASH TRANSFER DESK");
			jf.dispose();
			String aid = JOptionPane.showInputDialog(null, "Enter Account Number");
			new transferctr(name, aid);
		}
	}

	class statement implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String newpass = JOptionPane.showInputDialog(null, "Enter New Password");
			String query2 ="update adminlogs set password = '"+newpass+"' where postid ='"+pid+"' ";
			Connection con = DataBaseConnection.javaConnection();
			try {
				Statement stmt = con.createStatement();
				int rs = stmt.executeUpdate(query2);
				if(rs>0)
				{
					JOptionPane.showMessageDialog(null, "Password Change Sucessfully");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	class log implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Going TO Registration Panel \n Be Careful in Form Filling ");
			jf.dispose();
			new Registration();
		}
	}

	class submitbtn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "THANX FOR YOUR VALUABLE FEEDBACK");
			sta.setText("");
		}
	}
	class edit implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			String accn= JOptionPane.showInputDialog(null, "Enter Name Or Account Number");
			 ArrayList<edit_elist> elist= new ArrayList<edit_elist>();
				Connection cnt= DataBaseConnection.javaConnection();
				try {
					Statement stt= cnt.createStatement();
					String qq="SELECT * FROM accountinfo where accno = '"+accn+"' or name like '"+accn+"'";
					ResultSet rr= stt.executeQuery(qq);
					while(rr.next())
					{
						edit_elist err= new edit_elist();
						err.setAccno(rr.getString("accno"));
						err.setName(rr.getString("name"));
						err.setFname(rr.getString("fname"));
						err.setMobno(rr.getString("mobno"));
						err.setAddress(rr.getString("address"));
						err.setIdno(rr.getString("idno"));
						err.setEmail(rr.getString("email"));
						err.setDob(rr.getString("dob"));
						err.setAcctype(rr.getString("acctype"));
						err.setBalance(rr.getString("balance"));
						err.setPassword(rr.getString("password"));
						err.setGender(rr.getString("gender"));
						err.setSeq(rr.getString("seq"));
						err.setAns(rr.getString("ans"));
						elist.add(err);
					}
					
					jf.dispose();
					new Account_list(elist);
		}
				catch(SQLException er)
				{
					er.printStackTrace();
				}
		}
	}
}

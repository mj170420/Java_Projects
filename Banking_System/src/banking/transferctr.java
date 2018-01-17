package banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class transferctr {
	JFrame jf;
	JTextField utf, fntf, itf, etf, atf, mtf;
	JButton jb1, jb2;
	JLabel name, account, ifsc, amt, email, mno;
	String aname, aid;

	transferctr(String aname, String aid) {
		jf = new JFrame("TRANSFER SECTION");
		jf.setLayout(null);

		this.aname = aname;
		this.aid = aid;

		name = new JLabel("Name of Account Holder");
		name.setBounds(10, 23, 140, 50);
		jf.add(name);

		utf = new JTextField();
		utf.setBounds(150, 40, 200, 20);
		jf.add(utf);

		account = new JLabel("Account Number");
		account.setBounds(10, 53, 100, 50);
		jf.add(account);

		fntf = new JTextField();
		fntf.setBounds(150, 70, 200, 20);
		jf.add(fntf);

		ifsc = new JLabel("IFSC CODE");
		ifsc.setBounds(10, 83, 100, 50);
		jf.add(ifsc);

		amt = new JLabel("Amount To Be Transfer");
		amt.setBounds(10, 113, 200, 50);
		jf.add(amt);

		JLabel email = new JLabel("EMAIL ADDRESS");
		email.setBounds(10, 143, 100, 50);
		jf.add(email);

		JLabel mno = new JLabel("MOBILE NO.");
		mno.setBounds(10, 173, 100, 50);
		jf.add(mno);

		etf = new JTextField();
		etf.setBounds(150, 160, 200, 20);
		jf.add(etf);

		atf = new JTextField();
		atf.setBounds(150, 130, 200, 20);
		jf.add(atf);

		itf = new JTextField();
		itf.setBounds(150, 100, 200, 20);
		jf.add(itf);

		mtf = new JTextField();
		mtf.setBounds(150, 190, 200, 20);
		jf.add(mtf);

		jb1 = new JButton("TRANSFER");
		jb1.setBounds(250, 240, 100, 50);
		jb1.addActionListener(new transbt());
		jf.add(jb1);

		jb2 = new JButton("BACK");
		jb2.setBounds(140, 240, 100, 50);
		jb2.addActionListener(new backbt());
		jf.add(jb2);

		jf.setVisible(true);
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class transbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String accno; 
			int amt ;
			accno = fntf.getText();
			amt = Integer.parseInt(atf.getText());
			int ans = JOptionPane.showConfirmDialog(null, "Are you Sure ?");
			if (ans == JOptionPane.YES_OPTION) {
				Connection con = DataBaseConnection.javaConnection();
				try {
					Statement stt = con.createStatement();
					String qq= "select balance from accountinfo where accno='"+aid+"'";
					ResultSet rr= stt.executeQuery(qq);
					if(rr.next())
					{
						if(Integer.parseInt(rr.getString("balance"))>amt)
						{
							String qq1= "update accountinfo set balance=balance-"+amt+" where accno='"+aid+"'";
							int rr1= stt.executeUpdate(qq1);
							String qq2= "update accountinfo set balance=balance+"+amt+" where accno='"+accno+"'";
							int rr2= stt.executeUpdate(qq2);
							if(rr1>0 && rr2>0)
							{
								Date d= new Date();
								String inq= "insert into transaction(tdate, accno, balance, ttype) values ('"+d+"','"+aid+"','"+amt+"','Transfer-withdraw')";
								stt.executeUpdate(inq);
								String inq1= "insert into transaction(tdate, accno, balance, ttype) values ('"+d+"','"+accno+"','"+amt+"','transfer-deposit')";
								stt.executeUpdate(inq1);
								utf.setText("");
								fntf.setText("");
								itf.setText("");
								atf.setText("");
								etf.setText("");
								mtf.setText("");
								JOptionPane.showMessageDialog(null, "your balance is withdraw successfully");
							}
							else
							{
								String qq3= "update accountinfo set balance=balance+"+amt+" where accno='"+aid+"'";
								stt.executeUpdate(qq3);
								JOptionPane.showMessageDialog(null, "some error occured in updation");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "you dont have sufficient balance");
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "some error occured");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Transaction Failed");
			}
		}
	}

	class backbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			jf.dispose();
			new UserWelcome(aname, aid);
		}
	}

}

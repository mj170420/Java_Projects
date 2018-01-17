package banking;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class forgetpass implements ActionListener {
JFrame jf;
JLabel acclbl, seqlbl, anslbl , displbl;
JTextField anstf , accnotf;
JButton submitbtn, backbtn ;
String aid;

forgetpass(String aid)
{
	jf = new JFrame();
	jf.setLayout(null);
	this.aid = aid ;
	
	acclbl = new JLabel("ACCOUNT NUMBER");
	acclbl.setBounds(130, 30, 130, 20);
	jf.add(acclbl);
	
	accnotf = new JTextField(aid);
	accnotf.setBounds(250, 30, 130, 20);
	accnotf.setEditable(false);
	jf.add(accnotf);
	
	seqlbl = new JLabel("SECURITY QUESTION");
	seqlbl.setBounds(10, 60, 140, 20);
	jf.add(seqlbl);
	
	anslbl = new JLabel("ANSWER");
	anslbl.setBounds(10, 90, 100, 20);
	jf.add(anslbl);
	
	displbl = new JLabel();
	displbl.setBounds(150, 60, 300, 20);
	jf.add(displbl);
	
	anstf = new JTextField();
	anstf.setBounds(150, 90, 300, 20);
	jf.add(anstf);
	
	submitbtn = new JButton("SUBMIT");
	submitbtn.setBounds(230, 130, 100, 25);
	submitbtn.addActionListener(this);
	jf.add(submitbtn);

	backbtn = new JButton("BACK");
	backbtn.setBounds(100, 130, 100, 25);
	backbtn.addActionListener(new log());
	jf.add(backbtn);
	
	jf.setSize(600, 200);
	jf.setVisible(true);
	
	Connection con = DataBaseConnection.javaConnection();
	try {
		Statement stmt = con.createStatement();
		String query = "select seq from accountinfo where accno = '"+aid+"' ";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next())
		{
		String secuques = rs.getString("seq");
		displbl.setText(secuques);
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
}
public void actionPerformed(ActionEvent e)
	{
	String ans = anstf.getText(), ans1 = null ;
	Connection con = DataBaseConnection.javaConnection();
	try {
		Statement stmt = con.createStatement();
		String query = "select ans from accountinfo where accno = '"+aid+"' ";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next())
		{
			ans1 = rs.getString("ans");
		}
		if(ans.equals(ans1))
		{
			String pass1= JOptionPane.showInputDialog(null,"Enter new Password");
			String query1 = "update accountinfo set password = '"+pass1+"' where accno = '"+aid+"'";
			int n =stmt.executeUpdate(query1);
			if(n>0)
			{
				JOptionPane.showMessageDialog(null, "Password Changed Sucessfully");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error in Password Change Try Again Later!!!!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Wrong AnsWer!!!");
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	}
class log implements ActionListener
{

	public void actionPerformed(ActionEvent arg0) {
	jf.dispose();
	new loginform();
	}
	
}
}


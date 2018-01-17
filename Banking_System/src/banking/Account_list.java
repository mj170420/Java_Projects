package banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Account_list {
	JFrame jf ;
	JPanel jp ;
	JLabel accnolst, accname ;
	JComboBox accnocb = new JComboBox();
	JComboBox accnamecb ;
	JButton go;
	ArrayList<edit_elist> elist;
	Account_list(ArrayList<edit_elist> elist)
	{
		jf=new JFrame("Select Account");
		jp = new JPanel();
		jp.setLayout(null);
		
		this.elist = elist ;
		
		accnolst = new JLabel("Account Number");
		accnolst.setBounds(10, 10, 100, 20);
		jp.add(accnolst);
		
		accname = new JLabel("Account Name");
		accname.setBounds(10, 70, 100, 20);
		jp.add(accname);
		
		accnocb.setBounds(110, 10, 200, 20);
		accnocb.addItem("Select Your Account");
		for(edit_elist e : elist)
		{
			accnocb.addItem(e.getAccno());
		}
		jp.add(accnocb);
		
		accnamecb = new JComboBox();
		accnamecb.setBounds(110, 70, 200, 20);
		accnamecb.addItem("Select Your Name");
		for(edit_elist e : elist)
		{
			accnamecb.addItem(e.getName());
		}
		jp.add(accnamecb);
		
		go = new JButton("Edit Account");
		go.setBounds(150, 110, 150, 60);
		go.addActionListener(new editbtn());
		jp.add(go);
		
		jf.add(jp);
		jf.setVisible(true);
		jf.setSize(500, 330);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	class editbtn implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Connecting to server");	
		jf.dispose();
		new editacc(String.valueOf(accnocb.getSelectedItem()));
		}
	}
}

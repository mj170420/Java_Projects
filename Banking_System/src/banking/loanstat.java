package banking ;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.* ;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;

public class loanstat
{
	JFrame jf ;
	JPanel jp;
	JTable jt;
	JLabel titlelbl ;
	JButton backbtn ;
	Font ff = new Font("times new roman", Font.BOLD, 15);
	String data[][]={};
	String col[]={"Loan No.", "Issuing ACC. No.", "Issuing Amt.", "Time Alloted", "Rate of Interest", "Status"};
	String aid , name;
	public loanstat(String aid, String name)
	{
		jf = new JFrame("LOAN STATISTICS");
		jf.setLayout(new GridLayout(3, 0));
		  
		this.aid = aid ;
		this.name = name ;
		
		titlelbl = new JLabel("LOAN TABLE FOR STATUS OF PRESENT LOANS");
		titlelbl.setFont(new Font("times new roman", Font.BOLD, 48));
		titlelbl.setBounds(10, 10, 40, 40);
		jf.add(titlelbl);
		
		jt = new JTable(data , col);
		jt.setBounds(20, 70, 200, 300);
		
		JScrollPane js = new JScrollPane(jt);
		jf.add(js);
		
	     DefaultTableModel model=new DefaultTableModel(0, 0){public boolean isCellEditable(int row, int column){return false;}};
		model.setColumnIdentifiers(col);
		jt.setModel(model);
	     
		Connection con = DataBaseConnection.javaConnection();
		try {
			Statement stt = con.createStatement();
			String query = "Select * from loanreq where isaccno = '"+aid+"'";
			ResultSet rs = stt.executeQuery(query);
			while(rs.next())
			{
                String Loanno, Isaccno, Issuingamt, timealloted, rateofinterest, status ;
                Loanno=rs.getString("Loanno");
                Isaccno=rs.getString("Isaccno");
                status=rs.getString("status");
                Issuingamt=rs.getString("Issuingamt");
                timealloted=rs.getString("timealloted");
                rateofinterest=rs.getString("rateofinterest");
                model.addRow(new Object[] {Loanno, Isaccno, Issuingamt, timealloted, rateofinterest, status});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jp = new JPanel();
		backbtn = new JButton("BACK");
		backbtn.setBounds(80, 20, 10000, 40);
		backbtn.setFont(ff);
		backbtn.addActionListener(new backbt());
		jp.add(backbtn);
		
		
		jf.add(jp);
		jf.setSize(1050, 550);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	class backbt implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jf.dispose();
			new UserWelcome(name, aid);
		}
		
	}
}
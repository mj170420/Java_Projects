package banking ;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;

public class loan_window
{
	JFrame jf ;
	JTable jt;
	JPanel jp ;
	JLabel titlelbl ;
	JButton backbtn , statusbtn;
	Font ff = new Font("times new roman", Font.BOLD, 15);
	String data[][]={};
	String col[]={"Loan No.", "Issuing ACC. No.", "Issuing Amt.", "Time Alloted", "Rate of Interest", "Status"};
	String pid , name;
	public loan_window(String pid, String name)
	{
		jf = new JFrame("Loan Status");
		jf.setLayout(new GridLayout(3, 0));
		this.pid = pid ;
		this.name = name ;
		
		titlelbl = new JLabel("LOAN TABLE FOR STATUS OF PRESENT LOANS");
		titlelbl.setFont(new Font("times new roman", Font.BOLD, 48));
		titlelbl.setBounds(10, 10, 40, 40);
		jf.add(titlelbl);
		
		jt = new JTable(data , col);
		jt.setBounds(20, 70, 200, 300);
		
		JScrollPane js = new JScrollPane(jt);
		jf.add(js);
		
	     DefaultTableModel model=new DefaultTableModel(0, 0){public boolean isCellEditable(int row, int column){return true;}};
		model.setColumnIdentifiers(col);
		jt.setModel(model);
	     
		Connection con = DataBaseConnection.javaConnection();
		try {
			Statement stt = con.createStatement();
			String query = "Select * from loanreq ";
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
		backbtn.setBounds(150, 20, 1000, 50);
		backbtn.setFont(ff);
		backbtn.addActionListener(new backbt());
		jp.add(backbtn);
		
		statusbtn = new JButton("Save Edited Status");
		statusbtn.setBounds(60, 20, 1000, 50);
		statusbtn.setFont(ff);
		statusbtn.addActionListener(new estatus());
		jp.add(statusbtn);
		jf.add(jp);
		jf.setSize(1150, 700);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	class estatus implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int n = 0;
			JOptionPane.showMessageDialog(null, "Going To Save !!!!");
			for(int j = 0;j< jt.getRowCount();j++)
			{
				String stat = (String) jt.getModel().getValueAt(j, 5);
				String loan = (String) jt.getModel().getValueAt(j, 0);
				Connection con = DataBaseConnection.javaConnection();
				try {
					Statement stmt = con.createStatement();
					String q1 ="update loanreq set status = '"+stat+"' where Loanno = '"+loan+"'";
				 n = stmt.executeUpdate(q1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(n>0)
			{
				JOptionPane.showMessageDialog(null, "Sucessful !!");
			}
		}
		
	}
	class backbt implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jf.dispose();
			new Admin(name, pid);
		}
		
	}
}
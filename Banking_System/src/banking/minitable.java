package banking;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class minitable {
	
	JFrame jf;
	JPanel jp;
	JLabel snlb,tidlb,dtlb,amlb,acclb,ttplb;
	ArrayList<transactions> elist;
	JTextField sntf[],tidtf[],dttf[],amtf[],acctf[],ttptf[];
	JButton backbtn ;
	
	public minitable(ArrayList<transactions> elist) {
		
		
		sntf= new JTextField[elist.size()];
		tidtf=new JTextField[elist.size()];
		dttf=new JTextField[elist.size()];
		amtf=new JTextField[elist.size()];
		acctf=new JTextField[elist.size()];
		ttptf=new JTextField[elist.size()];
		this.elist= elist;
		 jf= new JFrame("Mini statement user");
		 jp= new JPanel();
		 jp.setLayout(null);
		 
		 snlb= new JLabel("S. No.");
		 snlb.setBounds(10, 10, 100, 25);
		 jp.add(snlb);
		 
		 tidlb= new JLabel("Transaction id");
		 tidlb.setBounds(120, 10, 100, 25);
		 jp.add(tidlb);
		 
		 acclb= new JLabel("Account No");
		 acclb.setBounds(230, 10, 150, 25);
		 jp.add(acclb);
		 
		 
		 amlb= new JLabel("Amount");
		 amlb.setBounds(340, 10, 150, 25);
		 jp.add(amlb);
		 
		 dtlb= new JLabel("date");
		 dtlb.setBounds(450, 10, 200, 25);
		 jp.add(dtlb);
		 
		 ttplb= new JLabel("Type");
		 ttplb.setBounds(560, 10, 150, 25);
		 jp.add(ttplb);
		 
		 int i=0,y=40;
		 for(transactions t:elist)
		 {
			 sntf[i]= new JTextField(String.valueOf(i+1));
			 sntf[i].setBounds(10, y, 100, 25);
			 sntf[i].setEditable(false);
			 jp.add(sntf[i]);
			 
			 tidtf[i]= new JTextField(t.getIdt());
			 tidtf[i].setBounds(120, y, 100, 25);
			 tidtf[i].setEditable(false);
			 jp.add(tidtf[i]);
			 
			 acctf[i]= new JTextField(t.getAccno());
			 acctf[i].setBounds(230, y, 100, 25);
			 acctf[i].setEditable(false);
			 jp.add(acctf[i]);
			 
			 amtf[i]= new JTextField(t.getBalance());
			 amtf[i].setBounds(340, y, 100, 25);
			 amtf[i].setEditable(false);
			 jp.add(amtf[i]);
			 
			 dttf[i]= new JTextField(t.getTdate());
			 dttf[i].setBounds(450, y, 100, 25);
			 dttf[i].setEditable(false);
			 jp.add(dttf[i]);
			 
			 ttptf[i]= new JTextField(t.getTtype());
			 ttptf[i].setBounds(560, y, 100, 25);
			 ttptf[i].setEditable(false);
			 jp.add(ttptf[i]);
			 
			 
			 i++;
			 y=y+30;
		 }
		 
	/*	 JScrollPane jsp = new JScrollPane(jp);
		 jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 jf.getContentPane().add(jsp);
		 */
		 
		 backbtn = new JButton("BACK");
		 backbtn.setBounds(500, 450, 100, 30);
		 backbtn.setBackground(Color.RED);
		 backbtn.addActionListener(new back());
		 jp.add(backbtn);
		 
		 jf.add(jp);
		 jf.setVisible(true);
		 jf.setSize(700, 550);
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

class back implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		jf.dispose();
		new loginform();
		
	}
	
}
	
}

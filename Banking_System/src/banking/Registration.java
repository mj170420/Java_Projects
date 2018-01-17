package banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Registration {
	JFrame jf;
	JTextField accnotf;
	JTextField nametf, fnametf, etf, mtf, idtf, sbaltf, anstf ;
	JButton jb1, jb2, jb3;
	JTextArea ata;
	JLabel accno, name, fname;
	ButtonGroup bg, bg1;
	JComboBox seq, idcombo, date, month, year;
	JRadioButton mrb, frb, srb, crb ;
	int accn, n ;
	
	Registration() {
		jf = new JFrame("Registration");
		jf.setLayout(null);

		accno = new JLabel("ACCOUNT NO.");
		accno.setBounds(10, 38, 100, 20);
		jf.add(accno);
  
		accnotf = new JTextField();
		accnotf.setText("Alloted By DataBase Automatically");
		accnotf.setBounds(150, 40, 200, 20);
		accnotf.setEditable(false);
		jf.add(accnotf);

		name = new JLabel("NAME");
		name.setBounds(10, 68, 100, 20);
		jf.add(name);

		fname = new JLabel("FATHER 'S NAME");
		fname.setBounds(10, 98, 100, 20);
		jf.add(fname);

		nametf = new JTextField();
		nametf.setBounds(150, 70, 200, 20);
		jf.add(nametf);

		fnametf = new JTextField();
		fnametf.setBounds(150, 100, 200, 20);
		jf.add(fnametf);

		JLabel gender = new JLabel("GENDER");
		gender.setBounds(10, 128, 100, 20);
		jf.add(gender);

		JLabel dob = new JLabel("DATE OF BIRTH");
		dob.setBounds(10, 158, 100, 20);
		jf.add(dob);

		JLabel email = new JLabel("EMAIL ADDRESS");
		email.setBounds(10, 188, 100, 20);
		jf.add(email);

		JLabel mno = new JLabel("MOBILE NO.");
		mno.setBounds(10, 218, 100, 20);
		jf.add(mno);

		JLabel add = new JLabel("ADDRESS");
		add.setBounds(10, 248, 100, 20);
		jf.add(add);

		etf = new JTextField();
		etf.setBounds(150, 190, 200, 20);
		jf.add(etf);

		 mtf = new JTextField();
		mtf.setBounds(150, 220, 200, 20);
		jf.add(mtf);

		date = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			date.addItem(String.valueOf(i));
		}
		date.setBounds(150, 160, 50, 20);
		jf.add(date);

		month = new JComboBox();
		for (int i = 1; i <= 12; i++) {
			month.addItem(String.valueOf(i));
		}
		month.setBounds(210, 160, 50, 20);
		jf.add(month);

		year = new JComboBox();
		for (long i = 1990; i <= 2020; i++) {
			year.addItem(String.valueOf(i));
		}
		year.setBounds(270, 160, 59, 20);
		jf.add(year);

		ata = new JTextArea();
		ata.setBounds(150, 250, 200, 100);
		ata.setLineWrap(true);
		jf.add(ata);

		mrb = new JRadioButton("MALE", true);
		mrb.setBounds(150, 130, 100, 20);
		jf.add(mrb);

		frb = new JRadioButton("FEMALE", false);
		frb.setBounds(250, 130, 100, 20);
		jf.add(frb);

		bg = new ButtonGroup();
		bg.add(mrb);
		bg.add(frb);

		JLabel Acctype = new JLabel("ACCOUNT TYPE");
		Acctype.setBounds(10, 368, 100, 40);
		jf.add(Acctype);

		srb = new JRadioButton("SAVING", true);
		srb.setBounds(150, 380, 100, 20);
		jf.add(srb);

		crb = new JRadioButton("CURRENT", false);
		crb.setBounds(250, 380, 100, 20);
		jf.add(crb);

		bg1 = new ButtonGroup();
		bg1.add(srb);
		bg1.add(crb);
		
		JLabel sequ = new JLabel("Select Security Question");
		sequ.setBounds(460, 38, 150, 20);
		jf.add(sequ);
		
		JLabel answer = new JLabel("Enter Your Answer");
		answer.setBounds(460, 68, 150, 20);
		jf.add(answer);
		
		JLabel Startbal = new JLabel("Enter Starting Balance");
		Startbal.setBounds(460, 98, 150, 20);
		jf.add(Startbal);
		
		sbaltf = new JTextField();
		sbaltf.setBounds(610, 98, 150, 20);
		jf.add(sbaltf);
		
		JLabel idsel = new JLabel("Select ID Proof Type");
		idsel.setBounds(460, 128, 150, 20);
		jf.add(idsel);
		
		idcombo = new JComboBox();
		idcombo.setBounds(610, 128, 300, 20);
		idcombo.addItem("Select Your ID Proof Type -");
		idcombo.addItem("Voter 's ID Card");
		idcombo.addItem("Aadhar Card");
		idcombo.addItem("Driving Licence");
		jf.add(idcombo);
		
		JLabel idno = new JLabel("Enter your Valid ID Numeration");
		idno.setBounds(420, 158, 250, 20);
		jf.add(idno);
		
		idtf = new JTextField();
		idtf.setBounds(610, 158, 150, 20);
		jf.add(idtf);
		
		seq = new JComboBox();
		seq.setBounds(610, 38, 400, 20);
		seq.addItem("Select a Security Question");
		seq.addItem("Which one is your fav.Cricket player ?");
		seq.addItem("What is your nickname ?");
		seq.addItem("What is your place of birth ?");
		seq.addItem("What is your mother 's name ?");
		seq.addItem("What is your favourite subject  ?");
		seq.addItem("What is your first love 's name ?");
		jf.add(seq);
		 anstf = new JTextField();
		anstf.setBounds(610, 68, 400, 20);
		jf.add(anstf);
		
		/*Connection con = DataBaseConnection.javaConnection();
		try {
			Statement stmt = con.createStatement();
			String query ="select count(*) from accountinfo";
			ResultSet rs1 = stmt.executeQuery(query);
			n = rs1.getInt(1);
			accn = 1668631+n ;
			accnotf.setText(String.valueOf(accn));
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		jb1 = new JButton("REGISTER");
		jb1.setBounds(610, 208, 100, 50);
		jb1.addActionListener(new regbt());
		jf.add(jb1);

		jb2 = new JButton("BACK");
		jb2.setBounds(810, 208, 100, 50);
		jb2.addActionListener(new backbt());
		jf.add(jb2);
		
		jf.setVisible(true);
		jf.setSize(1060, 590);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class regbt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "U ARE GOING TO REGISTER");
		String name, fname, mobno, address, idno, email, dob, acctype, balance, password, Gender, seq1, ans;
		Gender = "Male";
		acctype="Saving";
		dob = (String)(date.getSelectedItem()+" - "+month.getSelectedItem()+" - "+year.getSelectedItem());
		fname = fnametf.getText();
		mobno = mtf.getText();
		address = ata.getText();
		idno = idtf.getText();
		email = etf.getText();
		balance = sbaltf.getText();
		password = "12345678";
		seq1 = (String) seq.getSelectedItem();
		ans = anstf.getText();
		name = nametf.getText();
		if(frb.isSelected()==true)
		{
			Gender= "Female";
		}
		if(crb.isSelected()==true)
		{
			acctype ="Current";
		}
		Connection con = DataBaseConnection.javaConnection();
			try {
				Statement stmt = con.createStatement();
				String query = "insert into accountinfo (name, fname, mobno, address, idno, email, dob, acctype, balance, password, Gender, seq, ans) values ('"+name+"', '"+fname+"', '"+mobno+"', '"+address+"', '"+idno+"', '"+email+"', '"+dob+"', '"+acctype+"', '"+balance+"', '"+password+"', '"+Gender+"', '"+seq1+"', '"+ans+"')";
				stmt.executeUpdate(query);
				jf.dispose();
				new loginform();
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
	}

	class backbt implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Thanx");
			jf.dispose();
			new loginform();

		}

	}
}


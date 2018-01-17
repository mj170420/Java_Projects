import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class decCounter {
	JFrame jf ;
	JButton decButton, backButton;
	JLabel addLabel, fileLabel;
	JTextField addField, fileField;
	
	decCounter()
{
jf= new JFrame("decCounter");

addLabel = new JLabel("Address For File:");
addLabel.setBounds(20, 30, 100, 20);
jf.add(addLabel);

addField = new JTextField();
addField.setBounds(140, 30, 350, 30);
jf.add(addField);

fileLabel = new JLabel("Name of File:");
fileLabel.setBounds(20, 80, 100, 20);
jf.add(fileLabel);

fileField = new JTextField();
fileField.setBounds(140, 80, 350, 30);
jf.add(fileField);

decButton=new JButton("DECRYPT");
decButton.setBounds(30, 140, 200, 40);
decButton.addActionListener(new actionbt());
jf.add(decButton);

backButton=new JButton("Back To Main Counter");
backButton.setBounds(280, 140, 200, 40);
backButton.addActionListener(new bckbt());
jf.add(backButton);

jf.setLayout(null);
jf.setVisible(true);
jf.setSize(600, 250);
jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

}
	class bckbt implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jf.dispose();
			new soft1();
		}
		
	}
	class actionbt implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String keyText = "" ;
			try{
				SecretKey key = null;
			Cipher c = Cipher.getInstance("AES");
		String ans = JOptionPane.showInputDialog(null, "Enter Password");
		if(ans.equals("mayank")){
			String name =fileField.getText();
		String path=addField.getText()+"/"+name;
		String opath=addField.getText()+"/( "+name+" )decode.txt";
		String keypath = "src/Keys/key( demo1.txt ).txt";
	File log = new File(path);
	File newfile = new File(opath);
	File keyfile = new File(keypath);
	
		FileReader fr = new FileReader(log);
		FileWriter fw = new FileWriter(newfile);
		FileReader kr = new FileReader(keyfile);
		String ch ;
		BufferedReader br = new BufferedReader(fr);
		BufferedReader br1 = new BufferedReader(kr);
		while((ch=br1.readLine())!=null)
		{
			keyText=keyText+ch;
		}
		System.out.println(keyText);
		key=new SecretKeySpec(keyText.getBytes(), "AES");
			while((ch=br.readLine())!=null)
			{
					c.init(Cipher.DECRYPT_MODE, key, c.getParameters());
					byte[] cb=c.doFinal(ch.getBytes());
					fw.write(new String(cb));
				fw.close();
	}
		}
			}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
		}
		
	}
	public static void main(String[] a)
	{
		new decCounter();
	}
}
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class encCounter {
	JFrame jf ;
	JLabel addlabel, fileLabel;
	JTextField addfield , fileField;
	JButton encButton , backbtn;
	encCounter()
	{
		jf=new JFrame("EncCryptor");
		
		addlabel = new JLabel("Address For file");
		addlabel.setBounds(10, 30, 150, 30);
		jf.add(addlabel);
		
		addfield=new JTextField();
		addfield.setBounds(120, 30, 400, 30);
		jf.add(addfield);
		
		fileLabel = new JLabel("Name of File");
		fileLabel.setBounds(10, 90, 150, 30);
		jf.add(fileLabel);		

		fileField=new JTextField();
		fileField.setBounds(120, 90, 400, 30);
		jf.add(fileField);
		
		encButton=new JButton("Encrypt");
		encButton.setBounds(120, 140, 180, 30 );
		encButton.addActionListener(new actionbt());
		jf.add(encButton);
		
		backbtn= new JButton("Back to Main Counter");
		backbtn.setBounds(340, 140, 180, 30);
		backbtn.addActionListener(new actbtn());
		jf.add(backbtn);
		
		jf.setLayout(null);
		jf.setVisible(true);
		jf.setSize(700, 400);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		}
	class actbtn implements ActionListener
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
			try{
			KeyGenerator keygen=KeyGenerator.getInstance("AES/CBC/PKCS5Padding");
			keygen.init(128);
			SecretKey key = keygen.generateKey();
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String ans = JOptionPane.showInputDialog(null, "Enter Password");
		if(ans.equals("mayank")){
			String name =fileField.getText();
		String path=addfield.getText()+"/"+name;
		String opath=addfield.getText()+"/( "+name+" )code.txt";
		String keypath = "src/Keys/key( "+name+" ).txt";
	File log = new File(path);
	File newfile = new File(opath);
	File keyfile = new File(keypath);
	
		FileReader fr = new FileReader(log);
		FileWriter fw = new FileWriter(newfile);
		FileWriter kw = new FileWriter(keyfile);
		String ch ;
		BufferedReader br = new BufferedReader(fr);
			while((ch=br.readLine())!=null)
			{
					c.init(Cipher.ENCRYPT_MODE, key);
					byte[] cb=c.doFinal(ch.getBytes());
					fw.write(new String(cb));
				fw.close();
	}
			kw.write(key.toString());
			kw.close();
		}
			}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
		}
		}
/*	public static void main(String a[])
	{
	new encCounter();
}*/
}
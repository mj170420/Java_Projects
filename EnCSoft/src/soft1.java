import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class soft1 {
JFrame jf ;
JLabel HeadLabel, picLabel;
JButton jb1, jb2 ;
JPanel jp;
Font ff = new Font("times new roman", Font.BOLD, 30);
ImageIcon img ;
soft1()
{
	jf = new JFrame("EncSoft");
	jp = new JPanel();
	jp.setBackground(Color.RED);
		
	img= new ImageIcon("src/Pics/download.jpg");
	
	picLabel=new JLabel(img);
	picLabel.setBounds(140, 10, 271, 186);
	picLabel.setToolTipText("encSoft:A tool to encrypt or decrypt a file !!!");
	jp.add(picLabel);

	HeadLabel = new JLabel("What You want to do:!!!!!!!");
	HeadLabel.setBounds(10, 201, 500, 40);
	HeadLabel.setFont(ff);
	jp.add(HeadLabel);
	
	jb1=new JButton("Encrypt");
	jb1.setBounds(30, 251, 190, 80);
	jb1.addActionListener(new encbtn());
	jp.add(jb1);
	
	jb2=new JButton("Decrypt");
	jb2.setBounds(300, 251, 190, 80);
	jb2.addActionListener(new decbtn());
	jp.add(jb2);
	
	jp.setLayout(null);
	jf.add(jp);
	jf.setVisible(true);
	jf.setSize(550, 390);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
class encbtn implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Going to encyption center");
		jf.dispose();
		new encCounter();
	}
	
}
class decbtn implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Going to decryption center");
		jf.dispose();
		new decCounter();
	}
	
}
public static void main(String a[])
{
	new soft1();
}
}

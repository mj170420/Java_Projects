package chatroomServer;
import javax.swing.JFrame ;

public class ServerText {
public static void main(String a[])
{
	Server s1= new Server();
	s1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	s1.startRunning();
}
}

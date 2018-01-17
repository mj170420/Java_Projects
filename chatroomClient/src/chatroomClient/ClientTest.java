package chatroomClient;
import javax.swing.JFrame ;

public class ClientTest {
public static void main(String a[])
{
	Client c = new Client("127.0.0.1");
	c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	c.startRunning();
}
}

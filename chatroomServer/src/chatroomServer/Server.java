package chatroomServer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {

	private JTextField userText;
	private JTextArea chatWindow ;
	private ObjectOutputStream output ;
	private ObjectInputStream input ;
	private ServerSocket server ;
	private Socket connection ;
	public Server()
	{
		super("Mayank IM");
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sendMessage(e.getActionCommand());
						userText.setText("");
					}
				}
				);
		add(userText, BorderLayout.NORTH);
		chatWindow= new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300, 150);
		setVisible(true);
	}
	//set up and run server
	public void startRunning()
	{
		try {
			server = new ServerSocket(6789, 100);
			while(true)
			{
				try
				{
					waitForConnection();
					setupStream();
					whileChatting();
				}catch(EOFException eof)
				{
					showMessage("\n Server Ended the connection");
				}finally {
					closeCrap();
				}
			}
			
		}catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	//wait for connection, then display connection information
	private void waitForConnection() throws IOException
	{
		showMessage(" Waiting for someone to connect...\n");
		connection = server.accept();
		showMessage("Now Connected to "+connection.getInetAddress().getHostName());	
	}
	//get stream to send and receive data
	private void setupStream() throws IOException{
		output= new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are new Setup !!\n");
	} 
	//during the chat conversation
	private void whileChatting() throws IOException{
		String message = "You are now connected!!";
		sendMessage(message);
		ableToType(true);
		do {
			//have a conversation
			try {
				message = (String)input.readObject();
				showMessage("\n"+message);
			}catch(ClassNotFoundException c)
			{
				showMessage("\n idk wtf that user send !!");
			}
		}while(!message.equals("CLIENT - END"));
	}
	//close streams and sockets after you are done chatting
	private void closeCrap(){
		showMessage("\n Closing Connection ... \n");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		}catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	//send a message to client
	private void sendMessage(String message)
	{
		try {
			output.writeObject("SERVER-"+message);
			output.flush();
			showMessage("\nSERVER-"+message);
		}catch(IOException io)
		{
			chatWindow.append("\n ERROR : UNABLE TO SEND THE MESSAGE");
		}
	}
	//updates chatWindow
	private void showMessage(final String text)
	{
	SwingUtilities.invokeLater(
			new Runnable() {
				public void run()
				{
					chatWindow.append(text);
				}
			}
			);	
	}
	//let the user type stuff into their box
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run()
					{
						userText.setEditable(tof);
					}
				}
		);	
	}
}

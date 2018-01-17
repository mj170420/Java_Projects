package chatroomClient;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {

	private JTextField userText ;
	private JTextArea chatWindow;
	private ObjectOutputStream output ;
	private ObjectInputStream input ;
	private String message = "";
	private String serverIP ;
	private Socket connection ;
	public Client(String host) {
		super("Client mofo!!");
		serverIP= host ;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent a) {
						sendMessage(a.getActionCommand());
						userText.setText("");
					}
					}
				);
		add(userText, BorderLayout.NORTH);
		chatWindow = new  JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(300, 150);
		setVisible(true);
	} 	
	
	//connect to server
	public void startRunning() {
		try {
			connectToServer();
			setupStreams();
			whileChatting();
		}
		catch(EOFException eof)
		{
			showMessage("\n Client terminated the connection");
		}catch(IOException io)
		{
			io.printStackTrace();
		}finally {
			closeCrap();
		}
	}
	private void connectToServer() throws IOException{
		showMessage("Attempting Connection....\n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		showMessage("Connected to :"+connection.getInetAddress().getHostName());
	}
	//set up streams to send and receive message
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input= new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams Established!!\n");
	}
	//while chatting with server
	private void whileChatting() throws IOException{
		ableToType(true);
		do {
			try {
				message = (String)input.readObject();
				showMessage("\n "+message);
			}catch(ClassNotFoundException cnf)
			{
				showMessage("\n Unable to Send the message !!");
			}
		}while(!message.equals("SERVER-END"));
	}
	//close the streams and sockets
	private void closeCrap() {
		showMessage("\n closing crap down...");
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
	//send message to server
	private void sendMessage(String message)
	{
		try {
			
			output.writeObject("CLIENT - "+message);
			output.flush();
			showMessage("\n CLIENT - "+message);
		}catch(IOException io)
		{
			chatWindow.append("\n Error!!");
		}
	}
	//change/update chatWindow
	private void showMessage(final String m)
	{
		SwingUtilities.invokeLater(
				new Runnable() {

					@Override
					public void run() {
						chatWindow.append(m);
						
					}
					
				}
				);
	}
	// gives user permission to type
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(
				new Runnable() {

					@Override
					public void run() {
						userText.setEditable(tof);
						
					}
					
				}
				);
	}
}

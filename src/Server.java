import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Node{
	private ServerSocket serverSocket;
	private Socket socket;
	private Whiteboard whiteboard;

	public Server(int port) throws IOException{
		EventQueue.invokeLater(() -> {
			whiteboard = new Whiteboard();
			whiteboard.setVisible(true);
		});

		serverSocket = new ServerSocket(port, 0, InetAddress.getByName("localhost"));
		System.out.println("Hosting on " + serverSocket.getInetAddress());
		socket = serverSocket.accept();
		System.out.println("Connected to " + socket.getInetAddress());
	}

	public Server() throws IOException{
		this(1337);
	}


	@Override
	public void run() throws IOException{
		Utility.sendImage(socket, Utility.toBufferedImage(whiteboard.getImage()));
	}
}

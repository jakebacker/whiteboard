import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private Socket socket;

	public Server(int port) throws IOException{
		serverSocket = new ServerSocket(port, 0, InetAddress.getByName("localhost"));
		System.out.println("Hosting on " + serverSocket.getInetAddress());
		socket = serverSocket.accept();
		System.out.println("Connected to " + socket.getInetAddress());
	}

	public Server() throws IOException{
		this(1337);
	}



}

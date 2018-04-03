import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client implements Node {
	private Socket socket;
	private Whiteboard whiteboard;

	public Client(String ip, int port) throws IOException{
		EventQueue.invokeLater(() -> {
			whiteboard = new Whiteboard();
			whiteboard.setVisible(true);
			whiteboard.setTitle("Whiteboard | Client");
		});

		socket = new Socket(ip, port);

		System.out.println("Connected to " + socket.getInetAddress() + ":" + socket.getLocalPort());
	}

	public Client() throws IOException{
		this("localhost", 1337);
	}

	@Override
	public void run() throws IOException {
		Image image = Utility.receiveImage(socket);

		if (image != null) {
			whiteboard.drawImage(image);
		}
	}
}

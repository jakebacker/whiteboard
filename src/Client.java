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

		if (!Utility.recieveTest(socket)) {
			throw new IOException();
		} else {
			System.out.println("Connection Success");
		}
	}

	public Client() throws IOException{
		this("localhost", 1337);
	}

	@Override
	public void run() throws IOException {
		System.out.println("Run");
		if (whiteboard == null) {
			return;
		}
		Image tempImage = whiteboard.getImage();

		Image image = Utility.receiveImage(socket,tempImage != null ? Utility.toBufferedImage(whiteboard.getImage()) : null);

		if (image != null) {
			whiteboard.drawImage(image);
			whiteboard.repaint();
		}
	}
}

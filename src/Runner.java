import java.awt.*;

public class Runner {
	public static void main(String[] args) {
		String type = args[0];

		if (type.equals("Server")) {

		} else if (type.equals("Client")) {

		} else {
			throw new IllegalArgumentException();
		}

		EventQueue.invokeLater(() -> {
			Whiteboard whiteboard = new Whiteboard();
			whiteboard.setVisible(true);
		});
	}
}

import java.awt.*;
import java.io.IOException;

public class Runner {

	public static Mode mode;

	public static Node node;

	public static void main(String[] args) {
		String type = args[0];

		try {
			if (type.equals("Server")) {
				mode = Mode.SERVER;
				node = new Server();
			} else if (type.equals("Client")) {
				mode = Mode.CLIENT;

			} else {
				throw new IllegalArgumentException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

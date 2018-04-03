import java.awt.*;
import java.io.IOException;
import java.net.SocketException;

public class Runner {

	public static Mode mode;

	public static Node node;

	public static void main(String[] args) {
		String type = args[0].toLowerCase();

		try {
			if (type.equals("server")) {
				mode = Mode.SERVER;
				node = new Server();
			} else if (type.equals("client")) {
				mode = Mode.CLIENT;
				node = new Client();
			} else {
				throw new IllegalArgumentException();
			}
			while (true) {
				node.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

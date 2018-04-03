import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Utility {
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	public static void sendImage(Socket socket, BufferedImage image) throws IOException{
		OutputStream os = socket.getOutputStream();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);

		writeObject(objectOutputStream, image);
		os.flush();
//		os.close();
	}

	public static BufferedImage receiveImage(Socket socket) throws IOException, ClassNotFoundException {
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(inputStream);

		final BufferedImage image = readObject(ois);

//		inputStream.close();
		return image;
	}

	private static void writeObject(ObjectOutputStream out, final BufferedImage image) throws IOException {
//		out.defaultWriteObject();
		ImageIO.write(image, "png", out); // png is lossless
	}

	private static BufferedImage readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//		in.defaultReadObject();
		final BufferedImage image = ImageIO.read(in);
		return image;
	}



}

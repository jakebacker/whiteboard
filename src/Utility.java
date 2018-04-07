import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Utility {

	private static final String TEST_DATA = "Connected";

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

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", byteArrayOutputStream);

		byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
		os.write(size);
		os.write(byteArrayOutputStream.toByteArray());
		os.flush();
	}

	public static boolean sendTest(Socket socket){

		try {
			OutputStream os = socket.getOutputStream();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byteArrayOutputStream.write(TEST_DATA.getBytes());

			byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
			os.write(size);
			os.write(byteArrayOutputStream.toByteArray());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static BufferedImage receiveImage(Socket socket, BufferedImage previousImage) throws IOException{
		InputStream inputStream = socket.getInputStream();

		byte[] sizeAr = new byte[4];
		inputStream.read(sizeAr);
		int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

		if (size <= 0 || size >= Integer.MAX_VALUE) {
			return previousImage;
		}

		byte[] imageAr = new byte[size];
		inputStream.read(imageAr);

		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

		return image;
	}

	public static boolean recieveTest(Socket socket) {
		try {
			InputStream inputStream = socket.getInputStream();

			byte[] sizeAr = new byte[4];
			inputStream.read(sizeAr);

			int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

			if (size <= 0 || size >= Integer.MAX_VALUE) {
				throw new IOException();
			}

			byte[] dataAr = new byte[size];
			inputStream.read(dataAr);

			String data = new String(dataAr);

			if (data.equals(TEST_DATA)) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
}

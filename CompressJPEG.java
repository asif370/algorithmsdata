import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class CompressJPEG {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		final Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter file name");
		final String fileName = scanner.nextLine();

		System.out.println("Enter quality - between 0.0 (high compression) and 1.0 (no compression)");
		final float compression = scanner.nextFloat();

		File imageFile = new File(fileName);
		File compressedImageFile = new File("compressed_fileName");

		InputStream inputStream = new FileInputStream(imageFile);
		OutputStream outputStream = new FileOutputStream(compressedImageFile);

		float imageQuality = compression;

		//Create the buffered image
		BufferedImage bufferedImage = ImageIO.read(inputStream);

		//Get image writers
		Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

		if (!imageWriters.hasNext())
			throw new IllegalStateException("Writers Not Found!!");

		ImageWriter imageWriter = (ImageWriter) imageWriters.next();
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
		imageWriter.setOutput(imageOutputStream);

		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

		//Set the compress quality metrics
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setCompressionQuality(imageQuality);

		//Created image
		imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

		// close all streams
		inputStream.close();
		outputStream.close();
		imageOutputStream.close();
		imageWriter.dispose();
	}
}

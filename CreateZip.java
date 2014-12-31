import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.Scanner;

public class CreateZip {

    public static void main(String[] args) throws IOException {
        final ArrayList<File> files = new ArrayList<File>(args.length);

        for(String arg : args) { 
		    files.add(new File(arg));
        }

		CreateZip.packageZipFile(new File(args[0] + "_Zipped.zip"), files);
	}

	/**
	 * Packaging ZIP file
	 * @param output
	 * @param files
	 * @throws IOException
	 */
    public static void packageZipFile(File output, ArrayList<File> files) throws IOException
    {
        System.out.println("Packaging To " + output.getName());

        //Create ZipOutputStream to create Zip file
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(output));
        zipOutputStream.setLevel(Deflater.DEFAULT_COMPRESSION);

        //Iterate the files and zipping it
        for (File file : files)
        {
            if (file.isDirectory())
            {
                zipDirectory(zipOutputStream, "", file);
            } else
            {
                zipFile(zipOutputStream, "", file);
            }
        }
        zipOutputStream.flush();
        zipOutputStream.close();
        System.out.println("Zipping Done");
    }

    private static String buildPath(String filePath, String fileVar)
    {
        if (filePath == null || filePath.isEmpty())
        {
            return fileVar;
        } else
        {
            return filePath + "/" + fileVar;
        }
    }

    /**
     * Zipping Directory
     *
     * @param zipOutStream
     * @param filePath
     * @param directory
     * @throws IOException
     */
    private static void zipDirectory(ZipOutputStream zipOutStream, String filePath, File directory) throws IOException
    {
        if (!directory.canRead())
        {
            System.out.println("Cannot read " + directory.getCanonicalPath() + " (maybe because of permissions)");
            return;
        }

        File[] files = directory.listFiles();
        filePath = buildPath(filePath, directory.getName());
        System.out.println("Adding Directory " + filePath);

        for (File source : files)
        {
            if (source.isDirectory())
            {
                zipDirectory(zipOutStream, filePath, source);
            } else
            {
                zipFile(zipOutStream, filePath, source);
            }
        }

        System.out.println("Leaving Directory " + filePath);
    }

    /**
     * Zipping individual file
     *
     * @param zipOutStream
     * @param filePath
     * @param file
     * @throws IOException
     */
    private static void zipFile(ZipOutputStream zipOutStream, String filePath, File file) throws IOException
    {
    	//Check if file can be read
        if (!file.canRead())
        {
            System.out.println("Cannot read file : " + file.getCanonicalPath());
            return;
        }

        System.out.println("Compressing File : " + file.getName());
        zipOutStream.putNextEntry(new ZipEntry(buildPath(filePath, file.getName())));

        FileInputStream fileInStream = new FileInputStream(file);

        byte[] bufferVar = new byte[4092];
        int byteVar = 0;
        while ((byteVar = fileInStream.read(bufferVar)) != -1)
        {
            zipOutStream.write(bufferVar, 0, byteVar);
            System.out.print('.');
            System.out.flush();
        }
        fileInStream.close();
        zipOutStream.closeEntry();
    }
}

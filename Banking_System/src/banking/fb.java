package banking;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
public class fb {
	private static final boolean IS_CHUNKED = true;

	private static void encode(String sourceFile, String targetFile, boolean IS_CHUNKED) throws Exception
	{
		byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(sourceFile), IS_CHUNKED);
		writeByteArraysToFile(targetFile, base64EncodedData);
	}
	static void decode(String sourceFile, String targetFile) throws Exception
	{
		byte[] decodedBytes = Base64.decodeBase64(loadFileAsBytesArray(sourceFile));
		writeByteArraysToFile(targetFile, decodedBytes);
	}
	public static byte[] loadFileAsBytesArray(String fileName) throws Exception
	{
		File file = new File(fileName);
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		reader.close();
		return bytes;	
	}
	public static void writeByteArraysToFile(String fileName, byte[] content) throws IOException
	{
		File file = new File(fileName);
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(content);
		writer.flush();
		writer.close();
	}
}

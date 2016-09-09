package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;

import org.apache.commons.io.IOUtils;

public class DBHelper {
	
	public static Blob file2Blob(String filePath, Connection conn) {
		try {
			InputStream is = new FileInputStream(filePath);
			Blob blob = conn.createBlob();
			blob.setBytes(1, IOUtils.toByteArray(is));
			return blob;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static Clob file2Clob(String filePath, Connection conn) {
		try {
			Clob clob = conn.createClob();
			Writer writer = clob.setCharacterStream(1);
			writer.write(IOUtils.toCharArray(new FileReader(filePath)));
			writer.flush();
			writer.close();
			
			return clob;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static char[] readFileToCharArray(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
 
		return  fileData.toString().toCharArray();	
	}
}

package telran.io.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {
	byte[] buffer = new byte[1024 * 1024];
	int bufferSize = 0;
	
	public void copyfile(String sourceFile, String destinationFile, String command) throws Exception {
		try(InputStream input = new FileInputStream(sourceFile)){
			do {
			bufferSize = input.read(buffer);
			try(OutputStream output = new FileOutputStream(destinationFile)){
				output.write(buffer);
			}
			}while(bufferSize != -1);
		}
		
	}
}

package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReadBufferCopy implements CopyFile {
	
	
	int bufferSize;
	
	
	public ReadBufferCopy(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	@Override
	public void copyFiles(String sourseFile, String destinationFile) throws Exception {
		byte [] buffer = new byte[bufferSize];
		int bufferSizeChack = 0;
		try (InputStream input = new FileInputStream(sourseFile);
		         OutputStream output = new FileOutputStream(destinationFile)){
	
		while((bufferSizeChack = input.read(buffer))> 0) {
			output.write(buffer, 0, bufferSizeChack);
			
		
	}
	}
	}

}

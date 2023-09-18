package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

public class TransferToCopy implements CopyFile {

	@Override
	public void copyFiles(String sourseFile, String destinationFile)  throws Exception{
		try (InputStream input = new FileInputStream(sourseFile);
		         OutputStream output = new FileOutputStream(destinationFile)){
			long transferred = input.transferTo(output);
		}
		
	}

}

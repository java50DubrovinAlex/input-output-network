package telran.io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesCopyx implements CopyFile {

	@Override
	public void copyFiles(String sourseFile, String destinationFile) throws Exception {
		// TODO Auto-generated method Files.copy(null, null, null)
		try(InputStream input = new FileInputStream(sourseFile)){
			Files.copy(input, Paths.get(destinationFile));
		}
		
	}

}

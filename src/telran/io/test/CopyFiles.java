package telran.io.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class CopyFiles {
	byte[] buffer = new byte[1048576];
	int bufferSize = 0;
	long start;
	long finish;
	long spendTime;
	double sizeInByte;
	
	public String copyfile(String sourceFile, String destinationFile, String command) throws Exception {
		if(sourceFile == null || destinationFile == null) {
			return String.format("Too few arguments!");
		}
		if(!Files.exists(Paths.get(sourceFile))){
			return String.format(" Source file %s, must exist", sourceFile);
		}
		if(Paths.get(destinationFile).getParent() == null) {
			return String.format("The directory %s, must exist", Paths.get(destinationFile).getParent().toString());
		}
		if(command != "overwrite") {
			return String.format("File %s cannot be overwritten", sourceFile);
		}
		
		try (InputStream input = new FileInputStream(sourceFile);
		         OutputStream output = new FileOutputStream(destinationFile)){
		start =  System.currentTimeMillis();	
		while((bufferSize = input.read(buffer))> 0) {
			output.write(buffer, 0, bufferSize);
			sizeInByte += (double) bufferSize;
		}
		finish = System.currentTimeMillis();
		spendTime = finish - start;	
			
		}
//		catch(FileNotFoundException e) {
//			System.err.println("File not found: " + e.getMessage());
//		}
		
		
		return  String.format("File copied successfully from %s%n to %s%n, datasize: %.2f(mb), time spend: %d (ms) ", sourceFile, destinationFile,
				sizeInByte/1024/1024, spendTime);
	}
}

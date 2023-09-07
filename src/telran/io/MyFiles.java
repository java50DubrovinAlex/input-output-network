package telran.io;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class MyFiles {
public static void displayDir(String path, int maxDepth) throws IOException {
	// #one
	try {
		Files.walk(Path.of(path).toAbsolutePath().normalize(), maxDepth).forEach(p ->
		{ 
			printOffset(p.getNameCount());
			System.out.printf( "%s",p.getFileName());
			if(Files.isDirectory(p)) {
				System.out.println(" - " + "dir"); 
			}else {
				 System.out.println(" - " + "file");
			}
		});
	} catch (IOException e) {
		
	}
	
	
	
	}


private static void printOffset(int offsetNumbers) {
	for(int i = 0; i < offsetNumbers;i++) {
		System.out.print("   ");
	}
	
}
}

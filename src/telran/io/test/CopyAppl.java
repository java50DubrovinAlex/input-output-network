package telran.io.test;

public class CopyAppl {

	public static void main(String[] args) throws Exception {
		//args[0] - source file
		//args[2] - destination file
		//args[3] - "overwrite"
		//TODO write application for copying from source file to destination file
		//Implementation Requirement: to use while cycle with read call
		//main must not contain throws declaration
		CopyFiles copy = new CopyFiles();
		String sourceFile = args[0];
		String destinationFile = args[1];
		String command = args[2];
		copy.copyfile(sourceFile, destinationFile, command);
	}

}
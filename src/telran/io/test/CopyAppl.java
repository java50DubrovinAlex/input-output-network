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
		String sourceFile = "/Users/alexanderdubrovin/Desktop/from_copy/Free_Test_PDF.pdf";
		String destinationFile = "/Users/alexanderdubrovin/Desktop/from_copy/destination.pdf";
		String command ="overwrite";
		System.out.println(copy.copyfile(sourceFile, destinationFile, command));
	}

}
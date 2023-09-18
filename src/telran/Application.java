package telran;

import telran.io.CopyFile;
import telran.io.FilesCopyx;
import telran.io.ReadBufferCopy;
import telran.io.TransferToCopy;
import telran.io.performance.CopyFilesPerformanceTest;
import telran.io.test.CopyFiles;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "/Users/alexanderdubrovin/Desktop/from_copy/ideaIU-2022.3.3-aarch64.dmg";
		String dest =  "/Users/alexanderdubrovin/Desktop/from_copy/destination.dmg";
		CopyFiles readBuffer = new CopyFiles();
		ReadBufferCopy readBufferCopy =  new ReadBufferCopy(1024 * 1024);
		ReadBufferCopy readBufferCopy1 =  new ReadBufferCopy(1024 * 1024 * 1024);
		ReadBufferCopy readBufferCopy2 =  new ReadBufferCopy(5 * 1024);
		FilesCopyx filesCopyx = new FilesCopyx();
		TransferToCopy transferToCopy = new TransferToCopy();
		
		 CopyFilesPerformanceTest obj = new CopyFilesPerformanceTest("ReadBufferCopy", 1, src, dest, readBufferCopy);
		 CopyFilesPerformanceTest obj1 = new CopyFilesPerformanceTest("ReadBufferCopy", 1, src, dest, readBufferCopy1);
		 CopyFilesPerformanceTest obj2 = new CopyFilesPerformanceTest("ReadBufferCopy", 1, src, dest, readBufferCopy2);
		 CopyFilesPerformanceTest obj3 = new CopyFilesPerformanceTest("ReadBufferCopy", 1, src, dest, filesCopyx);
		 CopyFilesPerformanceTest obj4 = new CopyFilesPerformanceTest("ReadBufferCopy", 1, src, dest, transferToCopy);
		 
		 obj.run();
		 obj1.run();
		 obj2.run();
		 obj3.run();
		 obj4.run();

	}

}

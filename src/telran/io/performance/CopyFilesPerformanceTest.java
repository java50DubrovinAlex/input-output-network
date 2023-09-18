package telran.io.performance;

import telran.io.CopyFile;
import telran.performance.PerformanceTest;

public class CopyFilesPerformanceTest extends PerformanceTest {
	
	String sourceFile;
	String destinationFile;
	CopyFile copyFile;

	public CopyFilesPerformanceTest(String testName, int nRuns, String sourceFile, String destinationFile, CopyFile copyFile) {
		super(testName, nRuns);
		this.sourceFile = sourceFile;
		this.destinationFile = destinationFile;
		this.copyFile = copyFile;
	}

	@Override
	protected void runTest() throws Exception {	
		copyFile.copyFiles(this.sourceFile, this.destinationFile);
		
	}

}

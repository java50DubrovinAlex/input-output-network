package telran.io.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CommentsSeparation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		commentSeparator(args[0], args[1], args[2]);
	}
	private static void commentSeparator(String sourceFile, String destinationFileText, String destanationFileComment) throws IOException {
		try(Stream<String> lines = Files.lines(Paths.get(sourceFile));
				PrintWriter printComment = new PrintWriter(destanationFileComment); 
				PrintWriter printText = new PrintWriter(destinationFileText)){
			lines.forEach(line -> {
				if(Pattern.compile("^\\s*\\/\\/|^\\/\\/").matcher(line).find()) {
					printComment.println(line);
				}else {
					printText.println(line);
				}
			});
		}
	}
}

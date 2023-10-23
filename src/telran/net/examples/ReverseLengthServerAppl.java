package telran.net.examples;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class ReverseLengthServerAppl {
  static final int PORT = 6000;
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is listnening on port " + PORT);
		while(true) {
			Socket socket = serverSocket.accept();
			runProtocol(socket);
		}

	}
	private static void runProtocol(Socket socket)  {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream writer = new PrintStream(socket.getOutputStream())) {
			boolean running = true;
			while(running) {
				String request = reader.readLine();
				if (request == null) {
					System.out.println("client closed connection");
					running = false;
				} else {
					String response = getResponse(request);
					writer.println(response);
				}
			}
			
		} catch(Exception e) {
			System.out.println("Abnormal socket closing");
		}
		
	}
	private static String getResponse(String request) {
		// <type>#<string>
		String response = null;
		String[] tokens = request.split("#");
		if(tokens.length != 2) {
			response = "request must be in format <type>#<string>";
		} else {
			response = switch(tokens[0]) {
			case "reverse" -> new StringBuilder(tokens[1]).reverse().toString();
			case "length" -> tokens[1].length() + "";
			default -> "Wrong type";
			};
		}
		return response;
	}

}
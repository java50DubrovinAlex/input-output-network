package telran.view.hw_38;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import telran.view.Item;

public class OperationsServerAppl {
	private static final int PORT = 6000;

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
				System.out.println(request);
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
		String response = null;
		String [] token = request.split("#");
		if(token.length < 2) {
			response = "request must be in format <type>#<string> or <type>#<string>#<string>";
		}else {
			response = switch(token[0]) {
			case "add" -> Double.toString(Double.parseDouble(token[1]) + Double.parseDouble(token[2]));
			case "subtruct" -> Double.toString(Double.parseDouble(token[1]) - Double.parseDouble(token[2]));
			case "divide" -> Double.toString(Double.parseDouble(token[1]) / Double.parseDouble(token[2]));
			case "multiplay" -> Double.toString(Double.parseDouble(token[1]) * Double.parseDouble(token[2]));
			case "afterdays" -> LocalDate.now().plusDays(Long.parseLong(token[1])).toString();
			case "bifordays" -> LocalDate.now().minusDays(Long.parseLong(token[1])).toString();
			case "betweentodate" -> Long.toString(ChronoUnit.DAYS.between(LocalDate.parse(token[1]), LocalDate.parse(token[2]))); 
			default -> "Wrong type";
			};
		}
		return response;
	}
	
}

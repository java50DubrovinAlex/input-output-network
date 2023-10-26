package telran.view.hw_38;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import telran.view.InputOutput;

public class MyServer {
	 static final int PORT = 1000;
		public static void main(String[] args) throws Exception{
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
			    String[] tokens = request.split("#");

			    if (tokens.length != 3) {
			        response = "request must be in format <type>#<number1>#<number2>";
			    } else {
			       
			            String type = tokens[0];
			            double number1 = Double.parseDouble(tokens[1]);
			            double number2 = Double.parseDouble(tokens[2]);

			            switch (type) {
			                case "Add":
			                    response = String.valueOf(number1 + number2);
			                    break;
			                case "Subtract":
			                    response = String.valueOf(number1 - number2);
			                    break;
			                case "Multiply":
			                    response = String.valueOf(number1 * number2);
			                    break;
			                case "Divide":
			                    if (number2 != 0) {
			                        response = String.valueOf(number1 / number2);
			                    } else {
			                        response = "Division by zero is not allowed";
			                    }break;
			                case "After":
//			                    LocalDate date1 = LocalDate.parse(tokens[1]);
//			                    int daysToAdd = Integer.parseInt(tokens[2]);
//			                    LocalDate resultDate = date1.plusDays(daysToAdd);
//			                    response = resultDate.toString();
			                	response = tokens[1];
			                    break;

			                case "Before":
			                    LocalDate date2 = LocalDate.parse(tokens[1]);
			                    int daysToSubtract = Integer.parseInt(tokens[2]);
			                    LocalDate resultDate2 = date2.minusDays(daysToSubtract);
			                    response = resultDate2.toString();
			                    break;

			                case "Between":
			                    LocalDate date11 = LocalDate.parse(tokens[1]);
			                    LocalDate date22 = LocalDate.parse(tokens[2]);
			                    long daysBetween = ChronoUnit.DAYS.between(date11, date22);
			                    response = String.valueOf(daysBetween);
			                    break;
			                    
			                    
			                default:
			                    response = "Wrong type";
			            }
			    }
			    return response;
			}

}
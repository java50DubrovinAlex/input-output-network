package telran.view.hw_38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDate;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class OperationsClientAppl {
	
	private static final String HOST = "localhost";
	private static final int PORT = 6000;
	static  BufferedReader reader;
	static  PrintStream writer;

	public static void main(String[] args) throws Exception{
		InputOutput io = new SystemInputOutput();
		Socket socket = new Socket(HOST, PORT);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintStream(socket.getOutputStream());
		Item[] numberOperationsItem = getNumberOperationsItem();
		Item[] dateOperationsItem = getDateOperationsItem();
		Menu numberOperationsMenu = new Menu("Number operations", numberOperationsItem);
		Menu dateOperationsMenu = new Menu("Date operations", dateOperationsItem);
		Menu mainMenu = new Menu("Operations", numberOperationsMenu,  dateOperationsMenu, Item.of("Exit", io1 -> {
			try {
				socket.close();
			} catch (IOException e) {
				
			}
		}, true));
		mainMenu.perform(io);
	}
	private static Item[] getDateOperationsItem() {
		return new Item[] {
				Item.of(" Date after a given number of days", io1 -> getDatesOrNumberOfDays("afterdays", io1)),
				Item.of(" Date befor a given numbers of days", io1 -> getDatesOrNumberOfDays("bifordays", io1)),
				Item.of(" Days between two dates", io1 -> getDatesOrNumberOfDays("betweentodate", io1)),
				Item.exit()	
			};
	}
	private static Item[] getNumberOperationsItem() {
		
		
			return new Item[] {
					Item.of(" Add two numbers", io1 -> getOperands("add", io1) ),
					Item.of(" Subtruct two numbers", io1 -> getOperands("subtruct", io1)),
					Item.of(" Divide two numbers", io1 -> getOperands("divide", io1)),
					Item.of(" Multiplay two numbers", io1 -> getOperands("multiplay", io1)),
					Item.exit()
				};
		
	}
	private static void getOperands(String type, InputOutput io1) {
		
		 String [] token = new String [] {
				 
				io1.readString("Enter first number"),
				io1.readString("Enter second number")
		 };	
		 writer.printf("%s#%s#%s\n", type, token[0].toString(), token[1].toString());
		 try {
				io1.writeLine(reader.readLine());
			} catch (IOException e) {
				
			}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> void getDatesOrNumberOfDays(String type, InputOutput io1) {
		T [] token;
		if(type.equals("afterdays") || type.equals("bifordays")) {
			token = (T[]) new Object[] {
		            io1.readLong("Enter number of days", "Wrong number")
		        };
			writer.printf("%s#%s\n", type, token[0]);
			try {
				io1.writeLine(reader.readLine());
			} catch (IOException e) {
				
			}
		}else {
			boolean isEndDate = false;
			LocalDate startDate = io1.readIsoDate("Enter start date", "Wrong number");
			LocalDate endDate = null;
			while(!isEndDate) {
				endDate = io1.readIsoDate("Enter end date (End date must be great then start date)", "Wrong number");
				if(endDate.isAfter(startDate)) {
					isEndDate = true;
				}
			}
			token = (T[]) new Object [] {
					startDate,
					endDate
			};
			 writer.printf("%s#%s#%s\n", type, token[0], token[1]);
			 try {
					io1.writeLine(reader.readLine());
				} catch (IOException e) {
					
				}
		}
		
		
	}
	
}

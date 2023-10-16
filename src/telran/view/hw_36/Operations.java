package telran.view.hw_36;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class Operations {

	public static void main(String[] args) {
		Item[] itemMainMenu = getMainMenu();
		Menu menu = new Menu("Operations", itemMainMenu);
		menu.perform(new SystemInputOutput());
	}


	private static Item[] getMainMenu() {
		
		Item[] dateMenu = getItemDate();
		return new Item[] {
			Item.of(" Number Operations", Operations::openItemNumber),
			Item.of(" Data Operations",Operations::openItemDate),
			Item.exit()
		};
	}
	static void openItemNumber(InputOutput io) {
		Item[] numberSubMenu = getItemNumber();
		Menu numberMenu = new Menu ("Number Operations", numberSubMenu);
		numberMenu.perform(io);
		
		
    }
	
	static void openItemDate(InputOutput io) {
		Item[] dateSubMemu = getItemDate();
		Menu dateMenu = new Menu("Date Operations", dateSubMemu);
		dateMenu.perform(io);
    }
	//### subMenu block ###
	private static Item[] getItemNumber() {
		return new Item[] {
			Item.of(" Add two numbers", Operations::addItem),
			Item.of(" Subtruct two numbers", Operations::subtractItem),
			Item.of(" Divide two numbers", Operations::multiplyItem),
			Item.of(" Multiplay two numbers", Operations::divideItem),
			Item.exit()
		};
	}

	private static Item[] getItemDate() {
		return new Item[] {
			Item.of(" Date after a given number of days", Operations::dateAfter),
			Item.of(" Date befor a given numbers of days", Operations::dateBifor),
			Item.of(" Days between two dates", Operations::daysBitweenToDates),
			Item.exit()	
		};
	}
	//#### calc ####
	static void addItem(InputOutput io) {
		double[] operands = getOperands(io);
		io.writeObjectLine(operands[0] + operands[1]);
	}
	private static double[] getOperands(InputOutput io) {
		
		return new double[] {
				io.readDouble("Enter first number", "Wrong number"),
				io.readDouble("Enter second number", "Wrong number")
				
		};
	}

	static void subtractItem(InputOutput io) {
		double[] operands = getOperands(io);
		io.writeObjectLine(operands[0] - operands[1]);
	}
	static void multiplyItem(InputOutput io) {
		double[] operands = getOperands(io);
		io.writeObjectLine(operands[0] * operands[1]);
	}
	static void divideItem(InputOutput io) {
		double[] operands = getOperands(io);
		io.writeObjectLine(operands[0] / operands[1]);
	}

//	### date block ###
	private static Long getNumberOfDays(InputOutput io) {
		Long days = io.readLong("Enter number of days", "Wrong number");
		return days;
	}
	private static LocalDate[] getDates(InputOutput io) {
		boolean isEndDate = false;
		LocalDate startDate = io.readIsoDate("Enter start date", "Wrong number");
		LocalDate endDate = null;
		while(!isEndDate) {
			
			endDate = io.readIsoDate("Enter end date (End date must be great then start date)", "Wrong number");
			if(endDate.isAfter(startDate)) {
				isEndDate = true;
			}
		}
		return new LocalDate[] {
				startDate,
				endDate
		};
		
	}
	static void dateAfter(InputOutput io) {
		Long days = getNumberOfDays(io);
		LocalDate dateAfterDays = LocalDate.now().plusDays(days) ;
		io.writeObjectLine(dateAfterDays);
	}
	static void dateBifor(InputOutput io) {
		Long days = getNumberOfDays(io);
		LocalDate dateBiforDays = LocalDate.now().minusDays(days);
		io.writeObjectLine(dateBiforDays);
	}
	static void daysBitweenToDates(InputOutput io) {
		LocalDate [] dateArr = getDates(io);
		Long days = Math.abs(ChronoUnit.DAYS.between(dateArr[1], dateArr[0]));
		io.writeObjectLine(days);
	}
}

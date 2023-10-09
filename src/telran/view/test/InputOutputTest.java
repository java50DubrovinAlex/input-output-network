package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Expression;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.channels.Pipe;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javax.naming.spi.DirStateFactory.Result;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.dto.Employee;
import telran.view.*;

class InputOutputTest {
 InputOutput io = new SystemInputOutput();
 InputStream systemIn = System.in;
 
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testReadEmployeeString() {
		Employee empl = io.readObject("Enter employee <id>#<name>#<iso birthdate>#<department>#<salary>", "Wrong Employee",
				str -> {
					String[] tokens = str.split("#");
					if (tokens.length != 5) {
						throw new RuntimeException("must be 5 tokens");
					}
					long id = Long.parseLong(tokens[0]);
					String name = tokens[1];
					String department = tokens[3];
					int salary = Integer.parseInt(tokens[4]);
					LocalDate birthDate = LocalDate.parse(tokens[2]);
					return new Employee(id, name, department, salary, birthDate);
				});
		io.writeObjectLine(empl);
	}
	@Test
	void testReadEmployeeBySeparateField() throws IOException {
		//TODO
		//id in range [100000-999999]
		//name - more than two letters where first one is a capital
		//birthDate - any localdate in range [1950-12-31 - 2003-12-31
		//department - one out of ["QA", "Development", "Audit", "Accounting", "Management"
		//salary - integer number in range [7000 - 50000]
		HashSet<String> options = new HashSet<>();
		options.add("QA");
		options.add("Development");
		options.add("Audit");
		options.add("Accounting");
		options.add("Management");
		
		
		long id = (long) io.readData("Enter employee id", "The id must bee in range 100000000 - 999999999"
				, 100000000, 999999999, Comparator.naturalOrder() ,s ->{
					return (int) Long.parseLong(s);
				});
		
		String name = io.readString("Enter employee name", "The name must be more than two letters where first one is a capital",
				s -> s.matches("[A-Z][a-zA-Z]{2,}"));
		
		LocalDate birthDate = io.readData("Enter employee birth day",
				"The birth day must be in range [1950-12-31 - 2003-12-31]",
				LocalDate.of(1950, 12, 31), LocalDate.of(2003, 12, 31), Comparator.naturalOrder()
				, s -> {
					return LocalDate.parse(s);
				});
		
		String department = io.readString("Enter employee department"
				, "Department must be one out of [QA, Development, Audit, Accounting, Management",
				options);
		
		Integer salary = io.readData("Enter employee salary",
				"The salary must be  in range [7000 - 50000]", 7000, 50000, Comparator.naturalOrder(),
				s -> {
					return Integer.parseInt(s);
				});
		
		Employee emp = new Employee(id, name, department, salary, birthDate);
		
		io.writeObject("id: " + emp.id());
		io.writeObject(" name: " + emp.name());
		io.writeObject(" birth day: " + emp.birthDate());
		io.writeObject(" department: " + emp.department());
		io.writeObject(" salary: " + emp.salary());
	}
	@Test
	void testSimpleArithmeticCalculator() {
		HashSet<String> operations = new HashSet<>();
		operations.add("+");
		operations.add("-");
		operations.add("*");
		operations.add("/");
		
		Integer firstNumber = io.readInt("Enter first number", "The number must be in range [-2,147,483,648 -  2,147,483,647]");
		Integer secondNumber = io.readInt("Enter second number", "The number must be in range [-2,147,483,648 -  2,147,483,647]");
		String operation = io.readString("Enter operation",  "Operatikn must be one out of [+, -, *, /]", operations);
		
		String expression = "" + firstNumber + operation + secondNumber;
	}

}

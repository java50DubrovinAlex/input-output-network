package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import telran.employees.dto.Employee;
import telran.employees.service.CompanyImpl;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTests {
//TODO
	static String DATA_FILE_NAME = "dataFile.data";
	Employee [] employees = {
			new Employee(1234561, "Valera", "Back End", 17000, LocalDate.of(1982,12,07)),
			new Employee(1234562, "Igor", "Front End", 12000, LocalDate.of(1983,2,12)),
			new Employee(1234563, "Tolil", "Manager", 15000, LocalDate.of(1998,10,26)),
			new Employee(1234564, "Vera", "DevOps", 20000, LocalDate.of(1994,4,03))
	};
	CompanyImpl company = new CompanyImpl();
	
	
	@BeforeEach
	void setUp() throws Exception {
		for(Employee empl: employees) {
			company.addEmployee(empl);
		}
	}

	@Test
	void testAddEmployee() {
		assertTrue(company.addEmployee(new Employee(1234565, "Anna", "Back End", 20000, LocalDate.of(1983,4,17))));
		assertFalse(company.addEmployee(new Employee(1234565, "Anna", "Back End", 20000, LocalDate.of(1983,4,17))));
	}

	@Test
	void testRemoveEmployee() {
		Employee expected = employees[0];
		Employee expected1 = null;
		Employee removed = company.removeEmployee(1234561);
		Employee removed1 = company.removeEmployee(1234561);
		assertEquals(expected, removed);
		assertEquals(expected1, removed1);
		
		//TODO
	}

	@Test
	void testGetEmployee() {
		Employee expected = employees[0];
		Employee expected1 = null;
		assertEquals(expected, company.getEmployee(1234561));
		assertEquals(expected1, company.getEmployee(12345620));
		//TODO
	}

	@Test
	void testGetEmployees() {
		List<Employee> expected = Arrays.asList(employees);
		assertEquals(expected, company.getEmployees());
		//TODO
	}
	@Test
	@Order(2)
	void testRestore() {
		//TODO
	}
	@Test
	@Order(1)
	void testSave() {
		//TODO
	}

}

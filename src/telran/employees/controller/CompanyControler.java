package telran.employees.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.SalaryDistribution;
import telran.employees.service.Company;
import telran.view.InputOutput;
import telran.view.Item;
public class CompanyControler {
	private static final String []DEPARTMENTS = {"QA", "Development", "Audit", "Accounting", "Management"};
	private static final long MIN_ID = 100000;
	private static final long MAX_ID = 999999;
	private static final int MIN_SALARY = 7000;
	private static final int MAX_SALARY = 50000;
	private static final int MIN_YEAR = 1950;
	private static final int MAX_YEAR = 2001;
	private static Company company;
	public static ArrayList<Item> getItems(Company company) {
		CompanyControler.company = company;
		List<Item> itemsList = getItemsList();
		ArrayList <Item> res = new ArrayList<>(itemsList );
		return res;
	}
	private static List<Item> getItemsList() {
		
		return List.of(
				Item.of("Hire new Employee", CompanyControler::addEmployee),
				Item.of("Fire new Employee", CompanyControler::removeEmployee),
				Item.of("Display data of Employee", CompanyControler::getEmployee),
				Item.of("Display data of all Employees", CompanyControler::getEmployees),
				Item.of("Distribution of salary by department", CompanyControler::getDepartmentSalaryDistribution),
				Item.of("Salary distribution per interval", CompanyControler::getSalaryDistribution),
				Item.of("Display data of Employees in department", CompanyControler::getEmployeesByDepartment),
				Item.of("Display data of Employees by salary", CompanyControler::getEmployeesBySalary),
				Item.of("Display data of Employees by salary", CompanyControler::getEmployeesByAge),
				Item.of("Update salary", CompanyControler::updateSalary),
				Item.of("Update department", CompanyControler::updateDepartment)
				);
	}
	static void addEmployee(InputOutput io) {
		
		long id = io.readLong("Enter employee identity", "Wrong identity",MIN_ID, MAX_ID);
		if(company.getEmployee(id) == null) {
		String name = io.readString("Enter name", "Wrong name", str -> str.matches("[A-Z][a-z]{2,}"));
		String department = io.readString("Enter department", "Wrong department", new HashSet<String>(List.of(DEPARTMENTS)));
		int salary = io.readInt("Enter salarys", "Wrong salary", MIN_SALARY, MAX_SALARY);
		LocalDate birtDate = io.readIsoDate("enter birthdate in ISO form", "wrong birthdate", 
				LocalDate.of(MIN_YEAR, 1, 1), LocalDate.of(MAX_YEAR, 12, 31));
		Employee employee = new Employee(id, name, department, salary, birtDate);
		boolean res = company.addEmployee(employee );
		io.writeLine(res ? "Employee has been added" : "Employee already exists");
		}else {
			io.writeLine("Employee already exist");
		}
	}
	static void removeEmployee(InputOutput io) {
		//TODO
		Long id = io.readLong("Enter Employee id", "Wrong id");
		Employee removeEmpl = company.removeEmployee(id);
		io.writeLine(removeEmpl != null? 
				"The employee has been removed -> " + removeEmpl.toString()
				:"The employee doesn't exist");
		
	}
	static void getEmployee(InputOutput io) {
		Long id = io.readLong("Enter Employee id", "Wrong id");
		Employee employees = company.getEmployee(id);
		io.writeObjectLine(employees);
	}
	static void getEmployees(InputOutput io) {
		List<Employee> employees = company.getEmployees();
		employees.forEach(System.out::println);
	}
	static void getDepartmentSalaryDistribution(InputOutput io) {
		//TODO
		List<DepartmentSalary> departmentSalary = company.getDepartmentSalaryDistribution();
		departmentSalary.forEach(System.out::println);
	}
	static void getSalaryDistribution(InputOutput io) {
		int interval = io.readInt("Enter salary interval", "Wrong interval");
		List <SalaryDistribution> salaryDistribution = company.getSalaryDistribution(interval);
		salaryDistribution.forEach(System.out::println);
	}
	static void getEmployeesByDepartment(InputOutput io) {
		String department = io.readString("Enter department", "Wrong department"
				, new HashSet<String>(List.of(DEPARTMENTS)));
		List<Employee> employees = company.getEmployeesByDepartment(department);
		employees.forEach(System.out::println);
	}
	static void getEmployeesBySalary(InputOutput io) {
		int salaryFrom = io.readInt("Enter salary from", "Wrong salary from");
		int salaryTo = io.readInt("Enter salay to", "Wrong salary to");
		List<Employee> employees = company.getEmployeesBySalary(salaryFrom, salaryTo);
		employees.forEach(System.out::println);
	}
	static void getEmployeesByAge(InputOutput io) {
		int ageFrom = io.readInt("Enter age from", "Wrong age from");
		int ageTo = io.readInt("Enter age to", "Wrong age to");
		List<Employee> employees = company.getEmployeesByAge(ageFrom, ageTo);
		employees.forEach(System.out::println);
	}
	static void updateSalary(InputOutput io) {
		Long id = io.readLong("Enter Employee id", "Wrong id");
		int newSalary = io.readInt("Enter new salary", "Wrong salary");
		Employee newEmplSalary = company.updateSalary(id, newSalary);
		io.writeLine("Employee whit new salary" + newEmplSalary.toString());
	}
	static void updateDepartment(InputOutput io) {
		Long id = io.readLong("Enter Employee id", "Wrong id");
		String newDepartment= io.readString("Enter department", "Wrong department"
				, new HashSet<String>(List.of(DEPARTMENTS)));
		Employee newEmplDepartment = company.updateDepartment(id, newDepartment);
		io.writeLine("Employee whit new salary" + newEmplDepartment.toString());
		
	}
}

package telran.employees.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.SalaryDistribution;

public class CompanyImpl implements Company {
	HashMap<Long, Employee> employees = new HashMap<>(); //most effective structure for the interface methods
	TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
	HashMap<String, List<Employee>> employeesDepartment = new HashMap<>();
	
	@Override
	public boolean addEmployee(Employee empl) {
		boolean res = employees.putIfAbsent(empl.id(), empl) == null;
		if (res) {
			addEmployeesAge(empl);
			addEmployeesSalary(empl);
			addEmployeesDepartment(empl);
		}
		
		return res;
	}
//	private void addToIndexMap(Employee empl) {
//		
//	}
	private void addEmployeesAge(Employee empl) {
		int age = getAge(empl.birthDate());
		employeesAge.computeIfAbsent(age, k -> new LinkedList<>()).add(empl);
		
	}
	private void addEmployeesSalary(Employee empl) {
		employeesSalary.computeIfAbsent(empl.salary(), k -> new LinkedList<>()).add(empl);
	}
	private void addEmployeesDepartment(Employee empl) {
		employeesDepartment.computeIfAbsent(empl.department(), k -> new LinkedList<>()).add(empl);
	}

	@Override
	public Employee removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if(empl != null) {
			removeEmployeeAge(empl);
			removeEmployeesSalary(empl);
			removeEmployeesDepartment(empl);
		}
		
		return empl;
	}

	private void removeEmployeeAge(Employee empl) {
		int age = getAge(empl.birthDate());
		List<Employee> list = employeesAge.get(age);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesAge.remove(age);
		}
		
		
	}
	private void removeEmployeesSalary(Employee empl) {
		List<Employee> list = employeesSalary.get(empl.salary());
		list.remove(empl);
		if(list.isEmpty()) {
			employeesAge.remove(empl.salary());
		}
	}
	private void removeEmployeesDepartment(Employee empl) {
		List<Employee>list = employeesDepartment.get(empl.department());
		list.remove(empl);
		if(list.isEmpty()) {
			employeesDepartment.remove(empl.department());
		}
	}
	@Override
	public Employee getEmployee(long id) {
		
		return employees.get(id);
	}

	@Override
	public List<Employee> getEmployees() {
		return new ArrayList<>(employees.values());
	}

	@Override
	public List<DepartmentSalary> getDepartmentSalaryDistribution() {
		Map<String, Double> mapDepartmentSalary = employees.values().stream()
				.collect(Collectors.groupingBy(e -> e.department(), Collectors.averagingDouble(e -> e.salary())));
		return mapDepartmentSalary.entrySet().stream()
				.map(e -> new DepartmentSalary(e.getKey(), e.getValue())).toList();
	}

	@Override
	public List<SalaryDistribution> getSalaryDistribution(int interval) {
		Map<Integer, Long> mapIntervalNumbers = employees.values().stream()
				.collect(Collectors.groupingBy(e -> e.salary() / interval, Collectors.counting()));
		return mapIntervalNumbers.entrySet().stream()
				.map(e -> new SalaryDistribution(e.getKey() * interval, e.getKey() * interval + interval, e.getValue().intValue()))
				.sorted((sd1, sd2) -> Integer.compare(sd1.min(), sd2.min())).toList();
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		
		return employeesDepartment.get(department);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		
		return employeesSalary.subMap(salaryFrom, salaryTo).values().stream().flatMap(List::stream).toList();
	}

	@Override
	public List<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		
		
		return employeesAge.subMap(ageFrom, ageTo).values().stream().flatMap(List::stream).toList();
	}

	private int getAge(LocalDate birthDate) {
		
		return (int)ChronoUnit.YEARS.between(birthDate, LocalDate.now());
	}
	

	@Override
	public Employee updateSalary(long id, int newSalary) {
		Employee res = removeEmployee(id);
		Employee updateSalary = new Employee(id, res.name(), res.department(), newSalary, res.birthDate());
		addEmployee(updateSalary);
		return null;
	}

	@Override
	public Employee updateDepartment(long id, String department) {
		Employee res = removeEmployee(id);
		Employee updateDepartment = new Employee(id, res.name(), department, res.salary(), res.birthDate());
		addEmployee(updateDepartment);
		return null;
	}

}
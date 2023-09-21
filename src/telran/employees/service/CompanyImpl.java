package telran.employees.service;

import java.util.*;

import telran.employees.dto.Employee;

public class CompanyImpl implements Company {
	HashMap<Long, Employee> employees = new HashMap<>(); //most effective structure for the interface methods
	@Override
	public boolean addEmployee(Employee empl) {
		boolean res = true;
		if(employees.containsKey(empl.id())) {
			res = false;
		}else {
			employees.put(empl.id(), empl);
		}
		return res;
	}

	@Override
	public Employee removeEmployee(long id) {
		return employees.remove(id);
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}

	@Override
	public List<Employee> getEmployees() {
		List <Employee> allEmployees = new ArrayList<>(employees.values());
		return allEmployees;
	}

}

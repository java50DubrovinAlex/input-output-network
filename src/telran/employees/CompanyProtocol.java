package telran.employees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import telran.employees.dto.*;
import telran.employees.service.Company;
import telran.net.ApplProtocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class CompanyProtocol implements ApplProtocol {
	private Company company;

	public CompanyProtocol(Company company) {
		this.company = company;
	}

	@Override
	public Response getResponse(Request request) {
		Serializable requestData = request.requestData();
		String requestType = request.requestType();
		Response response = null;
		Serializable responseData = null;

		try {
			responseData = switch (requestType) {
			case "employee/remove" -> employee_remove(requestData);
			case "employees/departmentSalarys" -> employees_departmentSalarys(requestData);
			case "employees/salarysDistribution" -> employees_salarysDistribution(requestData);
			case "employees/byDepartment" -> employees_bydepartment(requestData);
			case "employees/bySalarys" -> employees_bysalary(requestData);
			case "employees/byAge" -> employees_byage(requestData);
			case "employee/department/update" -> employee_department_update(requestData);
			case "employee/add" -> employee_add(requestData);
			case "employee/get" -> employee_get(requestData);
			case "employees/all" -> employees_all(requestData);
			case "employee/salary/update" -> employee_salary_update(requestData);
			default -> Integer.MAX_VALUE;
			};
			response = responseData == (Integer)Integer.MAX_VALUE ? new Response(ResponseCode.WRONG_TYPE, requestType)
					: new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			response = new Response(ResponseCode.WRONG_DATA, e.getMessage());
		}

		return response;
	}

	private Serializable employee_department_update(Serializable requestData) {
		UpdateDepartment data = (UpdateDepartment) requestData;
		String newDepartment = data.newDepartment();
		long id = data.id();
		return company.updateDepartment(id, newDepartment);
	}

	private Serializable employees_byage(Serializable requestData) {
		int []ageRange = (int[]) requestData;
		return (Serializable) company.getEmployeesByAge(ageRange[0], ageRange[1]);
	}

	private Serializable employees_bysalary(Serializable requestData) {
		int [] salarys = (int[]) requestData;
		return (Serializable) company.getEmployeesBySalary(salarys[0], salarys[1]);
	}

	private Serializable employees_bydepartment(Serializable requestData) {
		
		return (Serializable) company.getEmployeesByDepartment((String)requestData);
	}

	private Serializable employees_salarysDistribution(Serializable requestData) {
		
		return (Serializable) company.getSalaryDistribution((int)requestData);
	}

	private Serializable employees_departmentSalarys(Serializable requestData) {
		
		return (Serializable) company.getDepartmentSalaryDistribution();
	}

	private Serializable employee_remove(Serializable requestData) {
		long id =  (long) requestData;
		return company.removeEmployee(id);
	}

	private Serializable employee_salary_update(Serializable requestData) {
		UpdateSalaryData data = (UpdateSalaryData) requestData;
		long id = data.id();
		int newSalary = data.newSalary();
		return company.updateSalary(id, newSalary);
	}

	private Serializable employees_all(Serializable requestData) {
		
		return new ArrayList<>(company.getEmployees());
	}

	private Serializable employee_get(Serializable requestData) {
		long id = (long) requestData;
		return company.getEmployee(id);
	}

	private Serializable employee_add(Serializable requestData) {
		Employee empl = (Employee) requestData;
		return company.addEmployee(empl);
	}

}

package telran.employees;

import java.util.ArrayList;

import telran.employees.controller.CompanyControler;
import telran.employees.service.*;
import telran.view.*;

public class CompanyAppl {

	private static final String DEFAULT_FILE_NAME = "employees.data";

	public static void main(String[] args) {
		InputOutput io = new SystemInputOutput();
		Company company = new CompanyImpl();
		String fileName = args.length > 0 ? args[0] : DEFAULT_FILE_NAME;
		company.restore(fileName);
		ArrayList<Item> items = CompanyControler.getItems(company);
		items.add(Item.of("Exit & Save", io1 -> company.save(fileName), true));
		Menu menu = new Menu("Company Applicastions", items );
		menu.perform(io);

	}

}

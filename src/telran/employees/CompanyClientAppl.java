package telran.employees;

import java.io.IOException;
import java.util.ArrayList;

import telran.employees.controller.CompanyController;
import telran.employees.net.CompanyNetworkProxy;
import telran.employees.service.*;
import telran.net.NetworkHandler;
import telran.net.TcpClientHandler;
import telran.view.*;

public class CompanyClientAppl {


	private static final String HOST = "localhost";
	private static final int PORT = 11000;
	public static void main(String[] args) {
		InputOutput io = new SystemInputOutput();
		try {
			 TcpClientHandler handler = new TcpClientHandler(HOST, PORT);
			Company company = new CompanyNetworkProxy(handler);
			ArrayList<Item> items = CompanyController.getItems(company);
			items.add(Item.of("Exit & Save", io1 -> {
				try {
					handler.close();
				} catch (IOException e) {
					io.writeLine(e.getMessage());
				}
			}, true));
			Menu menu = new Menu("Company Application", items );
			menu .perform(io);

		} catch (Exception e) {
			io.writeLine(e.getMessage());
		}
		
	}

}

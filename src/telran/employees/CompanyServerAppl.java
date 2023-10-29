package telran.employees;

import telran.employees.service.*;
import telran.net.ApplProtocol;
import telran.net.TcpServer;

public class CompanyServerAppl {

	private static final int PORT = 11000;
	private static final String DEFAULT_FILE_NAME = "employees";

	public static void main(String[] args) throws Exception {
		String fileName = args.length > 0 ? args[0] : DEFAULT_FILE_NAME;
		Company company = new CompanyImpl();
		company.restore(fileName);
		ApplProtocol protocol = new CompanyProtocol(company);
		TcpServer tcpServer = new TcpServer(PORT, protocol );
		tcpServer.run();

	}

}

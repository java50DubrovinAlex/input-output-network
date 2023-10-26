package telran.net;

public class NumbersDatesTcpServerAppl {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		NumbersDatesProtocol protocol = new NumbersDatesProtocol();
		TcpServer server = new TcpServer(9000, protocol);
		server.run();
	}

}

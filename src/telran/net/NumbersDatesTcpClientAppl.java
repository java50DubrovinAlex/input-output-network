package telran.net;

import java.util.ArrayList;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class NumbersDatesTcpClientAppl {
	public static void main(String[] args)throws Exception {
		InputOutput io = new SystemInputOutput();
		TcpClientHandler client = new TcpClientHandler("localhost", 9000);
		NumbersDatesProtocol protocol = new NumbersDatesProtocol();
		TcpClientServer connect = new TcpClientServer(client.socket, protocol);
		connect.run();
		Menu menu = new Menu("Operations", getItems());

		menu.perform(io);
	}

	private static ArrayList<Item> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

}

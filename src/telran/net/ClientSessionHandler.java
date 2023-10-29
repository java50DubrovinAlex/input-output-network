package telran.net;
import java.net.*;
import java.io.*;
public class ClientSessionHandler implements Runnable {
 Socket socket;
 ObjectInputStream reader;
 ObjectOutputStream writer;
 ApplProtocol protocol;
 public ClientSessionHandler(Socket socket, ApplProtocol protocol) throws Exception {
	 this.socket = socket;
	 this.protocol = protocol;
	 reader = new ObjectInputStream(socket.getInputStream());
	 writer = new ObjectOutputStream(socket.getOutputStream());
 }
	@Override
	public void run() {
		try {
			while(true) {
				Request request = (Request) reader.readObject();
				Response response = protocol.getResponse(request);
				writer.writeObject(response);
				writer.reset();
			}
			
		} catch (EOFException e) {
			System.out.println("Client closed connection");
		} catch (Exception e) {
			System.out.println("Abnormal closing connection");
		}

	}

}

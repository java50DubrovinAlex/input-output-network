package telran.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class TcpClientHandler implements Closeable, NetworkHandler {
	Socket socket;
	ObjectOutputStream writer;
	ObjectInputStream reader;
	public TcpClientHandler(String host, int port)throws Exception {
		socket = new Socket(host, port);
		writer = new ObjectOutputStream(socket.getOutputStream());
		reader = new ObjectInputStream(socket.getInputStream());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String requestType, Serializable requestData) {
		Request request = new Request(requestType, requestData);
		try {
			writer.writeObject(request);
			Response response = (Response) reader.readObject();
			if(response.code() != ResponseCode.OK) {
				throw new Exception(response.responsData().toString());
			}
			return (T) response.responsData();
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void close() throws IOException {
		socket.close();
		
	}

}

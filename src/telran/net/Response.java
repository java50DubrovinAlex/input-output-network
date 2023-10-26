package telran.net;

import java.io.Serializable;

public record Response(ResponseCode code, Serializable responsData) implements Serializable{

}

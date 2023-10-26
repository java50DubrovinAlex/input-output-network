package telran.net;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NumbersDatesProtocol implements ApplProtocol {

	@Override
	public  Response getResponse(Request request) {
		String[]token = ((String) request.requestData()).split("#");
		 
		String responseData = switch(request.requestType()) {
		case "add" -> Double.toString(Double.parseDouble(token[1]) + Double.parseDouble(token[2]));
		case "subtruct" -> Double.toString(Double.parseDouble(token[1]) - Double.parseDouble(token[2]));
		case "divide" -> Double.toString(Double.parseDouble(token[1]) / Double.parseDouble(token[2]));
		case "multiplay" -> Double.toString(Double.parseDouble(token[1]) * Double.parseDouble(token[2]));
		case "afterdays" -> LocalDate.parse(token[0]).plusDays(Long.parseLong(token[1])).toString();
		case "bifordays" -> LocalDate.parse(token[0]).minusDays(Long.parseLong(token[1])).toString();
		case "betweentodate" -> Long.toString(ChronoUnit.DAYS.between(LocalDate.parse(token[1]), LocalDate.parse(token[2]))); 
		default -> "Wrong type";
		};
		return new Response(ResponseCode.OK, responseData);
	}

}

package telran.view;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;


public interface InputOutput {
	String readString(String prompt);
	
	void writeString(String string);
	
	default void writeLine(String string) {
		writeString(string + "\n");
	}
	
	default void writeObject(Object object) {
		writeString(object.toString());
	}
	default void writeObjectLine(Object object) {
		writeLine(object + "\n");
	}
	default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
		boolean running = false;
		T res = null;
		do {
			running = false;
			String string = readString(prompt);
			try {
				res = mapper.apply(string);
				
			} catch (RuntimeException e) {
				writeLine(errorPrompt + ": " + e.getMessage());
				running = true;
			}
		}while(running);
		return res;
	}
	default Integer readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}
	default Integer readInt(String prompt, String errorPrompt, int min, int max) {
		return readObject(prompt, errorPrompt, s -> {
			Integer res = Integer.parseInt(s);
			return (res > min && res < max) ? res : null;
		});
	}
	default Long readLong(String prompt, String errorPrompt) {
		
		return readObject(prompt, errorPrompt, Long::parseLong);
	}
	default Long readLong(String prompt, String errorPrompt, long min, long max) {
		
		return readObject(prompt, errorPrompt, s -> {
			Long res = Long.parseLong(s);
			return (res < max && res > min) ? res : null;
		});
	}
	default Double readDouble(String prompt, String errorPrompt){
		
		return readObject(prompt, errorPrompt, Double::parseDouble);
	}
	default String readString (String prompt, String errorPrompt, Predicate<String> pattern){
		return readObject(prompt, errorPrompt, s -> {
			if(!pattern.test(s)) {
				throw new RuntimeException("Data entry is incorrect");
			}
			return s;
		});
	}
	default String readString(String prompt, String errorPrompt, HashSet<String> options){
		return readObject(prompt, errorPrompt, s -> {
			if(!options.contains(s)) {
				throw new RuntimeException("Data not found!");
			}
			return s;
			 
		});
	}
	default LocalDate readIsoDate(String prompt, String errorPrompt){
		
		return readObject(prompt, errorPrompt, LocalDate::parse);
	}
	default LocalDate readIsoDate(String prompt, String errorPrompt, LocalDate min, LocalDate max){
		return readObject(prompt, errorPrompt, s -> {
			LocalDate res = LocalDate.parse(s);
			if(res.isBefore(min) || res.isAfter(max)) {
				throw new RuntimeException("Date must be between: "+ min + max);
			}
			return res;
		});
	}
	default <T> T  readData(String prompt, String errorPrompt, T min, T max, Comparator<T> comp, Function<String, T> parser){
		return readObject(prompt, errorPrompt, s -> {
			T res = parser.apply(s);
			if(comp.compare(res, min) < 0 || comp.compare(res, max) > 0) {
				throw new RuntimeException("Data must be between: "+ min + max);
			}
			return res;		
		});
		
	}

	
}
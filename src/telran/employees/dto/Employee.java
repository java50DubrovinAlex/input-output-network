package telran.employees.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public record Employee(long id, String name, String department, int salary, LocalDate birthDate) implements Serializable {

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return id == other.id;
	}

}

package telran.employees.dto;

import java.io.Serializable;

public record UpdateDepartment(long id, String newDepartment) implements Serializable {

}

package training.employees.employees.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.employees.employees.gateway.Address;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class EmployeeDto {

    private Long id;

    @Schema(description="name of the employee", example = "John Doe")
    private String name;

    private Address address;

    public EmployeeDto(String name) {
        this.name = name;
    }

    public EmployeeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

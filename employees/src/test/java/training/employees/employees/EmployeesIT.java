package training.employees.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import training.employees.employees.controller.EmployeesController;
import training.employees.employees.service.CreateEmployeeCommand;
import training.employees.employees.service.EmployeeDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = "delete from employees")
public class EmployeesIT {

    @Autowired
    EmployeesController controller;

    @Test
    void testSaveAndList() {
        controller.createEmployee(new CreateEmployeeCommand("John Doe"));
        controller.createEmployee(new CreateEmployeeCommand("Jack Doe"));

        var employees = controller.listEmployees(Optional.of("j"));

        assertThat(employees).extracting(EmployeeDto::getName)
                .containsExactly("Jack Doe", "John Doe");
    }
}

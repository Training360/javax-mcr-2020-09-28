package training.employees.employees;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import training.employees.employees.controller.EmployeesController;
import training.employees.employees.gateway.AddressGateway;
import training.employees.employees.service.CreateEmployeeCommand;
import training.employees.employees.service.EmployeeDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = "delete from employees")
public class EmployeesIT {

    @MockBean
    AddressGateway addressGateway;

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

    @Test
    void testSaveAndFindById() {
        var employee = controller.createEmployee(new CreateEmployeeCommand("John Doe"));

        var loaded = controller.findEmployeeById(employee.getId());

        assertThat(loaded.getName()).isEqualTo("John Doe");
    }
}

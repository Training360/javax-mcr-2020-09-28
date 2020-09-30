package training.employees;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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

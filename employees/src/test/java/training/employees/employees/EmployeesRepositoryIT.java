package training.employees.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import training.employees.employees.repository.Employee;
import training.employees.employees.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeesRepositoryIT {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testSaveAndList() {
        employeeRepository.save(new Employee("John Doe"));
        employeeRepository.save(new Employee("Jack Doe"));
        var employees = employeeRepository.findByPrefix("j%");

        assertThat(employees)
                .extracting(Employee::getName)
                .containsExactly("Jack Doe", "John Doe");
    }
}

package training.employees.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where upper(e.name) like upper(:prefix) order by e.name")
    List<Employee> findByPrefix(String prefix);

    Optional<Employee> findFirstByName(String name);
}

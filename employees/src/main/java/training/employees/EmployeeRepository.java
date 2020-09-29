package training.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where upper(e.name) like upper(:prefix)")
    List<Employee> findByPrefix(String prefix);

    Optional<Employee> findFirstByName(String name);
}

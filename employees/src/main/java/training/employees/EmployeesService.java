package training.employees;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private final ModelMapper modelMapper;

    private static List<Employee> employees =
            Collections.synchronizedList(
                    new ArrayList<>(List.of(
                            new Employee(1L, "John Doe"),
                            new Employee(2L, "Jack Doe")
                    ))
            );

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        var filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());

        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        List<EmployeeDto> dtos = modelMapper.map(filtered, targetListType);
        return dtos;
    }

    public EmployeeDto findEmployeeById(long id) {
        var employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        return modelMapper.map(employee, EmployeeDto.class);
    }
}

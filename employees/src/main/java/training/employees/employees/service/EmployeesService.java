package training.employees.employees.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.employees.employees.gateway.AddressGateway;
import training.employees.employees.repository.Employee;
import training.employees.employees.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeesService {

    public static final String FEATURE_UNIQUE_CONTRAINT = "feature_unique_contraint";

    private FF4j ff4j;

    private final ModelMapper modelMapper;

    private EmployeeRepository repo;

    private AddressGateway addressGateway;

    @PostConstruct
    public void initFf4j() {
        ff4j.createFeature(new Feature(FEATURE_UNIQUE_CONTRAINT));
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        var employees = repo.findByPrefix(prefix.orElse("") + "%" );

        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        List<EmployeeDto> dtos = modelMapper.map(employees, targetListType);
        return dtos;
    }

    public EmployeeDto findEmployeeById(long id) {
        var employee = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        var address = addressGateway.getAddressByName(employee.getName());
        var dto = modelMapper.map(employee, EmployeeDto.class);
        dto.setAddress(address);
        return dto;
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        log.debug("Create employee with name: " + command.getName());
        log.info("Create employee");

        if (ff4j.check(FEATURE_UNIQUE_CONTRAINT)) {

            var employee = repo.findFirstByName(command.getName());
            if (employee.isPresent()) {
                throw new IllegalStateException("Employee exists with same name");
            }
        }

        var employee = new Employee(command.getName());
        repo.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        var employee = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        repo.deleteById(id);
    }
}

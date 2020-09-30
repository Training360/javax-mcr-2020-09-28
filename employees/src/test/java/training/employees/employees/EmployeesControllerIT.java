package training.employees.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import training.employees.employees.service.EmployeeDto;
import training.employees.employees.service.EmployeesService;
import training.employees.hello.service.HelloService;

import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
public class EmployeesControllerIT {

    @MockBean
    EmployeesService employeesService;

    @MockBean
    HelloService helloService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testList() throws Exception {
        when(employeesService.listEmployees(any()))
                .thenReturn(List.of(
                        new EmployeeDto(1L, "Jack Doe"),
                        new EmployeeDto(2L, "John Doe")
                ));

        mockMvc.perform(get("/api/employees")
        .param("prefix", "john"))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$[?(@.id == 2)].name", equalTo(List.of("John Doe"))))
        ;

        //verify(employeesService).listEmployees(Optional.of("john"));
        verify(employeesService).listEmployees(
                argThat(o -> o.get().startsWith("j")));

    }
}

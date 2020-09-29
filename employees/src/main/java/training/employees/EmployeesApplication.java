package training.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.findAndRegisterModules();
	}

//	@Bean
//	public HelloService helloService() {
//		return new HelloService(helloRepository());
//	}

	//	@Bean
//	public HelloService helloRepositry() {
//		return new HelloRepository();
//	}
}

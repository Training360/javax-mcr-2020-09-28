package training.employees;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
				.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
				.findAndRegisterModules();
	}

	@Bean
	public InMemoryAuditEventRepository inMemoryAuditEventRepository() {
		return new InMemoryAuditEventRepository();
	}

	@Bean
	public InMemoryHttpTraceRepository inMemoryHttpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Employees API")
						.version("1.0.0")
						.description("Operations with employees"));
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

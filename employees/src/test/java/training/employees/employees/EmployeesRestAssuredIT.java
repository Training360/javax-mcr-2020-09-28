package training.employees.employees;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.WebApplicationContext;
import training.employees.employees.service.CreateEmployeeCommand;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@Sql(statements = "delete from employees")
public class EmployeesRestAssuredIT {

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.requestSpecification = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        RestAssuredMockMvc
                .webAppContextSetup(webApplicationContext);
    }

    @Test
    void testCreateEmployee() {
        with().body(new CreateEmployeeCommand("John Doe")).
                when()
                .post("/api/employees")
                .then()
                .body("name", equalTo("John Doe"));

        with().body(new CreateEmployeeCommand("Jack Doe")).
                when()
                .post("/api/employees")
                .then()
                .body("name", equalTo("Jack Doe"));

        when()
                .get("/api/employees")
                .then()
                .body("[0].name", equalTo("Jack Doe"))
                .and()
                .body(matchesJsonSchemaInClasspath("employee-dto-schema.json"));
    }


}

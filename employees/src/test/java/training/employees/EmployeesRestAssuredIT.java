package training.employees;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
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
    @DirtiesContext
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
                .body("[0].name", equalTo("John Doe"))
                .and()
                .body(matchesJsonSchemaInClasspath("employee-dto-schema.json"))
        ;
    }


}

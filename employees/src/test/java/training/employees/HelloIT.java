package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloIT {

    @Autowired
    HelloController helloController;

    @Test
    void testSayHello() {
        assertThat(helloController.sayHello()).startsWith("Hello");
    }

    @Test
    void testSayHello2() {
        assertThat(helloController.sayHello()).startsWith("Hello");
    }

}

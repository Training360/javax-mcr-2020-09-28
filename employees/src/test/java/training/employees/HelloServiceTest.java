package training.employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        // Given - when - then
//        var service = new HelloService();
//        var message = service.sayHello();
//        assertThat(message).startsWith("Hello Spring");

        assertThat(new HelloService().sayHello()).startsWith("Hello Spring");
    }
}

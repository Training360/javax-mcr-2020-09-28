package training.employees.hello;

import org.junit.jupiter.api.Test;
import training.employees.hello.service.HelloProperties;
import training.employees.hello.service.HelloService;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        // Given - when - then
//        var service = new HelloService();
//        var message = service.sayHello();
//        assertThat(message).startsWith("Hello Spring");

        assertThat(new HelloService(new HelloProperties("Hello Spring")).sayHello()).startsWith("Hello Spring");
    }
}

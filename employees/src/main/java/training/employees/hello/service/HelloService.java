package training.employees.hello.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableConfigurationProperties(HelloProperties.class)
@AllArgsConstructor
public class HelloService {

    private HelloProperties helloProperties;

    public String sayHello() {
        return helloProperties.getWelcome() + LocalDateTime.now();
    }
}

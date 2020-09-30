package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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

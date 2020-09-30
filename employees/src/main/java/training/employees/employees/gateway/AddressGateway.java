package training.employees.employees.gateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import training.employees.hello.service.HelloProperties;

@Service
@EnableConfigurationProperties(GatewayProperties.class)
public class AddressGateway {

    private RestTemplate restTemplate;

    private GatewayProperties properties;

    public AddressGateway(GatewayProperties properties,
                          RestTemplateBuilder builder) {
        this.properties = properties;
        restTemplate = builder.build();
    }

    public Address getAddressByName(String name) {
        return restTemplate.getForObject(
                properties.getUrl(),
                Address.class,
                name
        );
    }
}

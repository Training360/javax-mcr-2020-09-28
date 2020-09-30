package training.employees.employees.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "employees.addresses")
@Data
public class GatewayProperties {

    private String url;
}

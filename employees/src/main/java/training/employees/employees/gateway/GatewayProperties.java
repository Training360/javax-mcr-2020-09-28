package training.employees.employees.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "employees.addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayProperties {

    private String url;
}

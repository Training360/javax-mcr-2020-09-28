package training.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@ConfigurationProperties(prefix = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class HelloProperties {

    @NotBlank
    private String welcome;
}

package dropwizard.demo.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DatabaseConfiguration {

    @NotEmpty
    @JsonProperty("url")
    private String dataBaseURL;

    @JsonProperty("username")
    private String dataBaseUsername;

    @JsonProperty("password")
    private String dataBasePassword;
}
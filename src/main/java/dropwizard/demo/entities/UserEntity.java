package dropwizard.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @JsonProperty("user_id")
    private int user_id;

    @JsonProperty("User_name")
    private String user_name;
}

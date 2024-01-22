package book.store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginDto {

    @NotNull(message = "Login required")
    @Size(min = 4,  message = "Login must be at least 4 characters long")
    private String login;

    @NotNull(message = "Password required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

}

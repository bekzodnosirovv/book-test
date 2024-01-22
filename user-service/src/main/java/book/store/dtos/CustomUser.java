package book.store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomUser {
    private Long id;
    private String role;
    private String login;
    private String password;

    public CustomUser(Long id, String role, String login, String password) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public CustomUser() {
    }
}

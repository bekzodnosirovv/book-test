package book.store.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDto {
    private Long id;
    private String role;
    private String login;
    private String password;
}

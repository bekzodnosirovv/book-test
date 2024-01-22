package book.store.entity;


import book.store.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
}

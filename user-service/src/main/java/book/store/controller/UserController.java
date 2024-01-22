package book.store.controller;

import book.store.dtos.CustomUser;
import book.store.dtos.UserDto;
import book.store.dtos.UserLoginDto;
import book.store.dtos.UserRegisterDto;
import book.store.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "User api list", description = "Api list for authorization and registration")
public class UserController {

    private final UserService userService;

    @Operation(summary = "user registration", description = "Method user for  Registration")
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        log.info("user registration {}", "");
        return ResponseEntity.ok(userService.registration(userRegisterDto));
    }

    @Operation(summary = "user login", description = "Method user for  login")
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        log.info("user login {}", "");
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    @Operation(summary = "get user details", description = "Method user for  get")
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Long id) {
        log.info("get user details {}", id);
        return ResponseEntity.ok(userService.getUserDetails(id));
    }


    @GetMapping("/custom/{login}")
    public CustomUser getUserDetails(@PathVariable String login) {
        log.info("get custom user {}", login);
        return userService.getCustomUser(login);
    }
}
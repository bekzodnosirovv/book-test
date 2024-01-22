package book.store.service;


import book.store.dtos.CustomUser;
import book.store.dtos.UserDto;
import book.store.dtos.UserLoginDto;
import book.store.dtos.UserRegisterDto;
import book.store.entity.UserEntity;
import book.store.enums.UserRole;
import book.store.exp.AppBadRequestException;
import book.store.exp.UserNotFoundException;
import book.store.feign.JwtServer;
import book.store.repository.UserRepository;
import book.store.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtServer jwtServer;

    public UserDto registration(UserRegisterDto userRegisterDto) {
        Optional<UserEntity> optionalUser = userRepository.findByLogin(userRegisterDto.getLogin());

        // check login unique
        if (optionalUser.isPresent()) {
            log.info("not registered login already exist {}", optionalUser.get().getLogin());
            throw new AppBadRequestException("Login already exist");
        }

        //user create
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRegisterDto.getName());
        userEntity.setRole(UserRole.CLIENT);
        userEntity.setLogin(userRegisterDto.getLogin());
        userEntity.setPassword(MD5Util.getMd5(userRegisterDto.getLogin())); // password shifrlab saqlanyapti
        //user save
        userRepository.save(userEntity);

        UserDto userDto =new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setToken(jwtServer.encode(userEntity.getLogin()));
        return userDto;
    }

    public UserDto login(UserLoginDto userLoginDto) {
        Optional<UserEntity> optionalUser = userRepository.findByLogin(userLoginDto.getLogin());

        // check login
        if (optionalUser.isEmpty()) {
            log.info("error login {}", userLoginDto.getLogin());
            throw new AppBadRequestException("Error login");
        }
        //check password
        if (!optionalUser.get().getPassword().equals(MD5Util.getMd5(userLoginDto.getLogin()))) {
            log.warn("error password {}", userLoginDto.getPassword());
            throw new AppBadRequestException("Error password");
        }

        UserDto userDto =new UserDto();
        userDto.setId(optionalUser.get().getId());
        userDto.setName(optionalUser.get().getName());
        userDto.setToken(jwtServer.encode(optionalUser.get().getLogin()));
        return userDto;

    }

    public UserDto getUserDetails(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            log.info("user not found {}", id);
            throw new UserNotFoundException("User not found");
        }
        UserDto userDto = new UserDto();
        userDto.setId(optionalUser.get().getId());
        userDto.setName(optionalUser.get().getName());
        userDto.setRole(optionalUser.get().getRole().name());
        return userDto;
    }

    public CustomUser getCustomUser(String login) {
        Optional<UserEntity> optionalUser = userRepository.findByLogin(login);

        if (optionalUser.isEmpty()) {
            log.info("error login {}", login);
            throw new AppBadRequestException("Error login");
        }
        return new CustomUser(optionalUser.get().getId(),optionalUser.get().getRole().name(),
                optionalUser.get().getLogin(),optionalUser.get().getPassword());
    }
}

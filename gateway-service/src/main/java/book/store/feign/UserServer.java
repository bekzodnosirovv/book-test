package book.store.feign;

import book.store.dtos.CustomUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient("user-service")
public interface UserServer {
    @PostMapping("custom/{login}")
    CustomUserDto getCustomUser(@PathVariable String login);


}

package book.store.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("auth-server")
public interface JwtServer {

    @PostMapping("encode/{login}")
    String encode(@PathVariable String login);

    @PostMapping("decode/{token}")
    Long decode(@PathVariable String token);
}

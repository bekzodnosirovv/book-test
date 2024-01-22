package book.store.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("auth-server")
public interface JwtServer {

    @PostMapping("encode/{id}")
    String encode(@PathVariable Long id);

    @PostMapping("decode/{token}")
    String decode(@PathVariable String token);
}

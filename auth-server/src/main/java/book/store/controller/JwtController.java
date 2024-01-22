package book.store.controller;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class JwtController {

    @Value("${jwt.tokenLifetimeInMinutes}")
    private int tokenLiveTime;
    @Value("${jwt.secretKey}")
    private String secretKey;

    @PostMapping("encode/{login}")
    public String encode(@PathVariable String login) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);
        jwtBuilder.claim("login", login);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("Book store");
        return jwtBuilder.compact();
    }

    @PostMapping("decode/{token}")
    public String decode(@PathVariable String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims claims = jws.getBody();
        return (String) claims.get("login");
    }
}

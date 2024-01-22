package book.store.config;

import book.store.util.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtTokenFilter;

    private final UserDetailsService userDetailsService;

    public static final String[] AUTH_WHITELIST = {
            "/api/v1/api-docs/**",
            "/api/v1/user/registration**",
            "/api/v1/user/login**"
    };
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers("/api/v1/user/get/**").hasAnyRole("ADMIN", "CLIENT")
                        .requestMatchers("/api/v1/book/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/book/**").hasAnyRole("ADMIN","CLIENT")
                        .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN","CLIENT")
                        .anyRequest().authenticated()
        ).addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5 = MD5Util.getMd5(rawPassword.toString());
                return md5.equals(encodedPassword);
            }
        };
    }

}

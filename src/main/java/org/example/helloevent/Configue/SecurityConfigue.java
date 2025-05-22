package org.example.helloevent.Configue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigue {
    public SecurityConfigue() {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth)
                        -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                        ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                               ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                              auth.requestMatchers(new String[]{"/", "/login", "/register", "/events/**"}))
                             .permitAll().requestMatchers(new String[]{"/admin/**"})).hasRole("ADMIN").
                               requestMatchers(new String[]{"/client/**"})).hasRole("CLIENT").anyRequest()).
                        authenticated()).formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

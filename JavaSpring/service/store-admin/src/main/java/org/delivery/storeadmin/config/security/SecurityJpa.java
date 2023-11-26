package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityJpa {
    private final List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(it->{
                    it
                            .requestMatchers(
                                    PathRequest.toStaticResources().atCommonLocations()
                            ).permitAll() // permit all static resources
                            .mvcMatchers(
                                    SWAGGER.toArray(new String[0])
                            ).permitAll() // permit swagger

                            .mvcMatchers(
                                    "/open-api/**"
                            ).permitAll() // permit open-api

                            .anyRequest().authenticated() // others authenticated
                            ;

                })
                .formLogin(Customizer.withDefaults())
                ;
        return httpSecurity.build();

    }

    @Bean // return object is registered bean
    public PasswordEncoder passwordEncoder(){
        // hash and salt (not able to decode)
        return new BCryptPasswordEncoder();
    }
}

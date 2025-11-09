package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth


                .requestMatchers("/api/users/register", "/api/users/login").permitAll()

                .requestMatchers("/api/users/{id}").authenticated()
      
                .requestMatchers("/api/users/**").hasAuthority("ADMIN")


                .requestMatchers("/api/products", "/api/products/**").permitAll()
                .requestMatchers("/api/products").hasAuthority("ADMIN")
                .requestMatchers("/api/products/**").hasAuthority("ADMIN")
                .requestMatchers("/api/cart/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                .requestMatchers("/api/orders/checkout").hasAnyAuthority("CUSTOMER", "ADMIN")
                .requestMatchers("/api/orders", "/api/orders/**").hasAnyAuthority("CUSTOMER", "ADMIN")

                .requestMatchers("/api/orders/*/status").hasAuthority("ADMIN")


                .anyRequest().authenticated()
            )
 
            .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

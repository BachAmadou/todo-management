package dev.javaNet.todomanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    // Configure password Encoder
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuring Basic Authentication
    // for a single user
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // NOTE: unfinished security configuration, and there is an error with the admin (we cannot access the data even when using the correct username and password).
        // will check the whole security configuration from the beginning.

        http.authorizeHttpRequests((authorize) -> {
            // all the users who have a role of "admin" can be able to access add todo, update todo and delete todo REST APIs.
            authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
            authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
            authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // For multiple users
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails bach = User.builder()
                .username("bach")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(bach, admin);
    }

}

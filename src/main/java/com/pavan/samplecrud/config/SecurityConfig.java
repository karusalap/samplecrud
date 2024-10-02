package com.pavan.samplecrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/employees/**").permitAll()
                        .requestMatchers("/payment").hasRole("CARD-OWNER")
                        .requestMatchers("/**").permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    PasswordEncoder buildEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService buildUserDetailsService(PasswordEncoder passwordEncoder) {
        User.UserBuilder builder = User.builder();
        UserDetails adminDetails = builder.username("admin").password(passwordEncoder.encode("password"))
                .roles("ADMIN").build();
        UserDetails userDetails = builder.username("user").password(passwordEncoder.encode("password"))
                .roles("USER").build();
        return new InMemoryUserDetailsManager(adminDetails, userDetails);
    }
}

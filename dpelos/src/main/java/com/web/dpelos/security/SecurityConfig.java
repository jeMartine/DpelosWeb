package com.web.dpelos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            /* H2 */
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/dueno/**").hasAnyAuthority("DUENO", "ADMINISTRADOR", "VETERINARIO")
                .requestMatchers("/vet/**").hasAnyAuthority("VETERINARIO", "ADMINISTRADOR")
                .requestMatchers("/admin/**").hasAnyAuthority("ADMINISTRADOR")
                .requestMatchers("/dueno/details").hasAuthority("DUENO")
                .requestMatchers("/vet/details").hasAuthority("VETERINARIO")
                .anyRequest().permitAll()
            )
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));

            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /*Metodo para encriptar contraseñas*/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}
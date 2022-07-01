package com.ironhack.MidtermBankingSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    //------------------- A editar con mis rutas -------------------//
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(); // Vamos a utilizar basic auth
        http.csrf().disable(); // Desactivamos la protección CSRF porque nosotros no vamos a manejar el HTML
        http.authorizeRequests() // Vamos a estacler la protección de cada endpoint individualmente
                .antMatchers(HttpMethod.GET, "/accountHolders").hasRole("ADMIN") // solo usuarios autenticados
//                .antMatchers(HttpMethod.POST, "/accountHolders").hasRole("ADMIN") // Solo ADMIN
                //.antMatchers(HttpMethod.GET, "/accountHolders").hasRole("ADMIN")
                //.antMatchers(").hasAnyRole("OWNERS") // Solo ADMIN y TECHNICIAN
                .anyRequest().permitAll(); // El resto de los enpoints son públicos
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
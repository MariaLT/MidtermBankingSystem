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
                .antMatchers(HttpMethod.POST, "/savings").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/checkings").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/creditcards").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/thirdparties").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/accountHolders").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/roles").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/{id}/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/savinginterest").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/creditcardinterest").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/maintenance").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/penaltyfee").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/{id}/modifybalance").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accountHolders").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/accounts/{id}/balance").hasRole("OWNER")
                .antMatchers(HttpMethod.GET,"/accounts/{id}/transfer").hasRole("OWNER")
                .anyRequest().permitAll(); // El resto de los enpoints son públicos
        return http.build();
//        @PatchMapping("/accounts/{hashedKey}/receivemoney")
//        @PatchMapping("/thirdparties/{hashedKey}/sendmoney")

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
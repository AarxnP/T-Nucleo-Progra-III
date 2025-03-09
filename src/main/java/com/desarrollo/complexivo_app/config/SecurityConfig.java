package com.desarrollo.complexivo_app.config;

import com.desarrollo.complexivo_app.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin((FormLoginConfigurer<HttpSecurity> form) -> form.loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll());
        http.authorizeHttpRequests(( authorizer)-> authorizer
                .requestMatchers("/login", "/register").permitAll()
                .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );
        return http.build();
    }

    //Usuario en bruto
  /*@Bean
    public UserDetailsManager users(){
        UserDetails user = User.builder()
                .username("vflores")
                .password("{bcrypt}$2a$10$1ewUM6QYE8omItp5TGWDVuevVgYkeFeGNksK6uIGTf1/ag6lOabQK")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);

    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


}

package com.house.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class HouseUserSecurityConfig {

	

    @Bean
public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}



private static final String[] UN_SECURED_URLs = { 

        "/question_menage/**",
        "/constantes_param/**",
        "/dashBord/**",
        "/rapport/**",
        "/historique/**",
        "/exercice/**",
        "/houseHold/getHouseholdsByIdExercice/**",
        "/houseHold/**",
        "/enquete/**",
        "/users/**",
        "/swagger-ui/index.html#/**",
        "/users/login/**",
        "/swagger-ui.html/**"
};



@Bean
public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
return httpSecurity
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
                .requestMatchers(UN_SECURED_URLs).permitAll()

                .requestMatchers("security")
                .hasAnyRole("admin")
                .anyRequest()
                .authenticated()
        )
//        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(Customizer.withDefaults())
        .build();
}




}

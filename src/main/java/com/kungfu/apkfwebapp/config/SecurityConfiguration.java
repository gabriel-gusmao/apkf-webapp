//package com.kungfu.apkfwebapp.config;
//
//import com.kungfu.apkfwebapp.services.MyUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfiguration {
//
//    private final MyUserDetailsService myUserDetailsService;
//
//    public SecurityConfiguration(MyUserDetailsService myUserDetailsService) {
//        this.myUserDetailsService = myUserDetailsService;
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .userDetailsService(myUserDetailsService)
//                .httpBasic(withDefaults())
//                .build();
//    }
//
//}

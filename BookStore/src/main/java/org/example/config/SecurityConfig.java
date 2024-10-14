package org.example.config;


import org.example.filter.JWTRequestFilter;
import org.example.filter.UserAuthenticationFilter;
import org.example.utils.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public UserAuthenticationFilter userAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        UserAuthenticationFilter filter = new UserAuthenticationFilter(jwtUtils);
        filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher.antMatcher(POST, "/login"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, UserDetailsService userDetailsService,
                                           UserAuthenticationFilter filter, JWTUtils jwtUtils) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/register", "/a/css/**", "/a/script/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(customizer -> customizer
                        .loginPage("/login")
                        .defaultSuccessUrl("/main_store_book", true)
                        .permitAll())
                .addFilter(filter)
                .addFilterAfter(new JWTRequestFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService);

        return httpSecurity.build();
    }


}

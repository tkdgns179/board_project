package com.example.board_project.config;

import com.example.board_project.config.jwt.JwtAuthenticationFilter;
import com.example.board_project.config.jwt.JwtAuthorizationFilter;
import com.example.board_project.domain.user.UserRepository;
import com.example.board_project.config.jwt.ExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class)
            .addFilter(corsFilter)
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
            .authorizeRequests()
            .antMatchers("/api/user/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') ")
            .antMatchers("/api/manager/**")
            .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') ")
            .antMatchers("/api/admin/**")
            .access("hasRole('ROLE_ADMIN') ")
            .anyRequest()
            .permitAll();
    }
}

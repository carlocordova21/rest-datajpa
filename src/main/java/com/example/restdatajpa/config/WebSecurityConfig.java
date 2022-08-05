package com.example.restdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final String[] SWAGGER_PATTERNS = {
            "/swagger-resources/**",
            "/v2/api-docs",
            "/swagger-ui/**",
    };

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(SWAGGER_PATTERNS).hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET,"/api/laptops").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/laptops/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "/api/laptops").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/laptops/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/laptops/**").hasRole("ADMIN")
			.anyRequest().denyAll()
		)
                .httpBasic()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

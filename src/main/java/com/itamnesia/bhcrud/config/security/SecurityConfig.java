package com.itamnesia.bhcrud.config.security;

import com.itamnesia.bhcrud.model.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${cors.allowed.origin.pattern}")
    private String allowedOriginPattern;
    private final OncePerRequestFilter jwtAuthenticationFilter;
    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(configurer -> configurer
                        .antMatchers("/api/v1/announcements/latest").permitAll()
                        .antMatchers("/api/v1/auth/**", "/api/v1/expert/auth/**").permitAll()
                        .antMatchers("/api/v1/confirmation/**").hasAuthority(Role.ACTIVE_UNCONFIRMED.name())
                        .antMatchers("/api/v1/expert/confirmation/**").hasAuthority(Role.EXPERT_UNCONFIRMED.name())
                        .antMatchers("/api/v1/announcements/**").hasAuthority(Role.ACTIVE.name())
                        .antMatchers("/api/v1/expert/**").hasAuthority(Role.EXPERT.name())
                        .antMatchers("/api/v1/experts").hasAuthority(Role.ACTIVE.name())
                        .antMatchers("/api/v1/experts/**").hasAuthority(Role.EXPERT.name())
                        .antMatchers("/api/auth/**", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern(allowedOriginPattern);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        var configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    @Bean
    public Filter corsHeaderFilter() {
        return (request, response, chain) -> {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
            chain.doFilter(request, response);
        };
    }

}

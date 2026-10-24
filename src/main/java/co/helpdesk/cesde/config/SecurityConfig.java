package co.helpdesk.cesde.config;

import co.helpdesk.cesde.infrastructure.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtFilter;

  public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public AuthenticationManager authenticationManagerBean(
      AuthenticationConfiguration configuration
  ) throws Exception {
    return configuration.getAuthenticationManager();
  }
  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http)
      throws Exception {

    return http

        .csrf(csrf -> csrf.disable())

        .sessionManagement(session ->
              session.sessionCreationPolicy(
                  SessionCreationPolicy.STATELESS
              ))

        .authorizeHttpRequests(auth -> auth

            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/auth/**")
            .permitAll()

            .anyRequest()
            .authenticated())
        .addFilterBefore(
            jwtFilter,
            UsernamePasswordAuthenticationFilter.class
        )
        .build();
  }
}
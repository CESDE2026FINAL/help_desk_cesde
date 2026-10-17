package co.helpdesk.cesde.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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

        .authorizeHttpRequests(auth -> auth

            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/auth/**")
            .permitAll()

            .anyRequest()
            .authenticated())

        .build();
  }
}
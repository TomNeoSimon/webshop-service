package de.fhdw.tomneosimon.sicherheitsaspekte.config.security;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
  private final AuthenticationErrorHandler authenticationErrorHandler;

  @Value("${okta.oauth2.groups-claim:groups}")
  private String groupsClaim;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.jwtAuthenticationConverter(makePermissionsConverter()))
            .authenticationEntryPoint(authenticationErrorHandler)
        );

    return http.build();
  }

  private JwtAuthenticationConverter makePermissionsConverter() {
    final var converter = new JwtAuthenticationConverter();

    converter.setJwtGrantedAuthoritiesConverter(jwt -> {
      final Collection<GrantedAuthority> authorities = new ArrayList<>();
      final Object claim = jwt.getClaim(groupsClaim);

      if (claim instanceof String string) {
        authorities.add(new SimpleGrantedAuthority(string));
      } else if (claim instanceof Collection<?>) {
        for (Object c : (Collection<?>) claim) {
          if (c != null) {
            authorities.add(new SimpleGrantedAuthority(c.toString()));
          }
        }
      }
      return authorities;
    });

    return converter;
  }
}
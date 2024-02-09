package in.codingstreams.etuserauthservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors(AbstractHttpConfigurer::disable);
    httpSecurity.csrf(AbstractHttpConfigurer::disable);

    httpSecurity.authorizeHttpRequests(
        requestMatcher->requestMatcher.anyRequest().permitAll()
    );

    return httpSecurity.build();
  }
}

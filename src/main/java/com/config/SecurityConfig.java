package com.config;

import com.entity.Roles;
import com.entity.User;
import com.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private UserServiceImpl userService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public InMemoryUserDetailsManager defaultUser() {
    UserDetails user_test = (UserDetails) User
      .builder()
      .id(1L)
      .firstName("user")
      .lastName("test")
      .email("user@gmail.com")
      .password(passwordEncoder().encode("userPass"))
      .role(userService.getAuthorities(Roles.USER))
      .build();
    UserDetails admin_test = (UserDetails) User
      .builder()
      .id(2L)
      .firstName("admin")
      .lastName("test")
      .email("admin@gmail.com")
      .password(passwordEncoder().encode("adminPass"))
      .role(userService.getAuthorities(Roles.ADMIN))
      .build();
    return new InMemoryUserDetailsManager(user_test, admin_test);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(crsf -> crsf.disable())
      .authorizeHttpRequests(auth ->
        auth
          .antMatchers("/registration**", "/js/**", "/css/**", "/img/**")
          .permitAll()
          .antMatchers("/students/**")
          .hasAnyAuthority("ROLE_ADMIN")
          .anyRequest()
          .authenticated()
      )
      .formLogin(form ->
        form
          .loginPage("/login")
          .defaultSuccessUrl("/students")
          .loginProcessingUrl("/login")
          .failureUrl("/login?error=true")
          .permitAll()
      )
      .logout(logout ->
        logout
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .invalidateHttpSession(true)
          .clearAuthentication(true)
          .logoutSuccessUrl("/?login")
          .permitAll()
      )
      .build();
  }
}

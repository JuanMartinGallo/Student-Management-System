package com.model;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class UserDTO {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Set<GrantedAuthority> role;
}

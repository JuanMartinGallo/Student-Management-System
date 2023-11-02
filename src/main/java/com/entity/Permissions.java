package com.entity;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
public enum Permissions {
  USER_READ(new SimpleGrantedAuthority("USER_READ")),
  USER_WRITE(new SimpleGrantedAuthority("USER_WRITE"));

  private final GrantedAuthority authority;

  public GrantedAuthority getAuthority() {
    return authority;
  }
}

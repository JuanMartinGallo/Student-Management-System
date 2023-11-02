package com.service;

import com.entity.Permissions;
import com.entity.Roles;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface BaseService {
  default Set<GrantedAuthority> getAuthorities(Roles role) {
    Set<GrantedAuthority> roles = role
      .getPermissions()
      .stream()
      .map(Permissions::getAuthority)
      .collect(Collectors.toSet());

    roles.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
    return roles;
  }
}

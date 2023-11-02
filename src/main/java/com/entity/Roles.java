package com.entity;

import java.util.Set;

public enum Roles {
  USER(Set.of(Permissions.USER_READ)),
  ADMIN(Set.of(Permissions.USER_READ, Permissions.USER_WRITE));

  private final Set<Permissions> permissions;

  Roles(Set<Permissions> Permissions) {
    this.permissions = Permissions;
  }

  public Set<Permissions> getPermissions() {
    return permissions;
  }
}

package com.dms.springqrcodeapi.enums;

import static com.dms.springqrcodeapi.enums.Permission.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN(
    Set.of(
      ADMIN_READ,
      ADMIN_UPDATE,
      ADMIN_DELETE,
      ADMIN_CREATE,
      USER_READ,
      USER_UPDATE,
      USER_DELETE,
      USER_CREATE
    )
  ),
  USER(Set.of(USER_READ, USER_UPDATE, USER_DELETE, USER_CREATE));

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
      .stream()
      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
      .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}

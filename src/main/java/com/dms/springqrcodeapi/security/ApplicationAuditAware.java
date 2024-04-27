package com.dms.springqrcodeapi.security;

import com.dms.springqrcodeapi.entities.User;
import io.micrometer.common.lang.NonNull;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationAuditAware implements AuditorAware<Integer> {

  @Override
  @NonNull
  public Optional<Integer> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext()
      .getAuthentication();
    if (
      authentication == null ||
      !authentication.isAuthenticated() ||
      authentication instanceof AnonymousAuthenticationToken
    ) {
      return Optional.empty();
    }

    User userPrincipal = (User) authentication.getPrincipal();
    return Optional.ofNullable(userPrincipal.getId());
  }
}

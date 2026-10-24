package co.helpdesk.cesde.infrastructure.security.provider;

import org.springframework.stereotype.Component;

import co.helpdesk.cesde.application.service.auth.IdentityProvider;
import co.helpdesk.cesde.domain.model.Usuario;

@Component
public class LocalIdentityProvider
    implements IdentityProvider {

  @Override
  public Usuario authenticate(
      String username,
      String password) {

    throw new UnsupportedOperationException(
        "Implementación local pendiente");
  }
}
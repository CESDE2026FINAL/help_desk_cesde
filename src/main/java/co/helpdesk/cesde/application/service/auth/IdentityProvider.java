package co.helpdesk.cesde.application.service.auth;

import co.helpdesk.cesde.domain.model.Usuario;

public interface IdentityProvider {

  Usuario authenticate(String username, String passwor);
}

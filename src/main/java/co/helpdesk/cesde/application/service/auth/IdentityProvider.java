package co.helpdesk.cesde.application.service.auth;

import co.helpdesk.cesde.domain.model.User;

public interface IdentityProvider {

  User authenticate(String username, String password);
}

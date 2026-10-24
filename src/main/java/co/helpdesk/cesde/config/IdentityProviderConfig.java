package co.helpdesk.cesde.config;

import co.helpdesk.cesde.application.service.auth.IdentityProvider;
import co.helpdesk.cesde.infrastructure.security.provider.LocalIdentityProvider;
import co.helpdesk.cesde.infrastructure.security.provider.SsoIdentityProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityProviderConfig {

  @Bean
  public IdentityProvider identityProvider(
      @Value("${security.auth-mode}") String mode,
      LocalIdentityProvider localProvider,
      SsoIdentityProvider ssoProvider) {

    return "SSO".equalsIgnoreCase(mode)
        ? ssoProvider
        : localProvider;
  }
}
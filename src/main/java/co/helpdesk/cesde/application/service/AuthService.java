package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.auth.AuthResponse;
import co.helpdesk.cesde.application.dto.auth.LoginRequest;
import co.helpdesk.cesde.application.dto.auth.RegisterRequest;

public interface AuthService {

  AuthResponse login(LoginRequest request);

  AuthResponse register(RegisterRequest request);

}

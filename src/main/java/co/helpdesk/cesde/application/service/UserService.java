package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.user.UserDeleteResponse;
import co.helpdesk.cesde.application.dto.user.UserResponse;

public interface UserService {

  UserDeleteResponse eliminarUsuario(Long id);

  UserResponse desactivarUsuario(Long id);

  UserResponse activarUsuario(Long id);
}

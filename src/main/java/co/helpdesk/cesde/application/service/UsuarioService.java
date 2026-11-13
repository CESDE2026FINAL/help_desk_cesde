package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.user.UsuarioDeleteResponse;

public interface UsuarioService {

  UsuarioDeleteResponse eliminarUsuario(Long id);

}

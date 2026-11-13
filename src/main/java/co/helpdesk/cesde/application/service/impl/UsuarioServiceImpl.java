package co.helpdesk.cesde.application.service.impl;

import co.helpdesk.cesde.application.dto.user.UsuarioDeleteResponse;
import co.helpdesk.cesde.application.service.UsuarioService;
import co.helpdesk.cesde.domain.exception.UsuarioNoEncontradoException;
import co.helpdesk.cesde.infrastructure.persistence.entity.UsuarioEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.UsuarioJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioJpaRepository usuarioRepository;

  @Override
  @Transactional
  public UsuarioDeleteResponse eliminarUsuario(Long id) {

    UsuarioEntity usuario = usuarioRepository
        .findById(id)
        .orElseThrow(() ->
            new UsuarioNoEncontradoException(id));

    usuario.setActivo(false);

    usuarioRepository.save(usuario);

    return new UsuarioDeleteResponse(
        "Usuario desactivado correctamente");

  }

}


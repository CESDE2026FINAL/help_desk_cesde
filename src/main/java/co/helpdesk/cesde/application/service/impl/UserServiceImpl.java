package co.helpdesk.cesde.application.service.impl;

import co.helpdesk.cesde.application.dto.user.UserDeleteResponse;
import co.helpdesk.cesde.application.dto.user.UserResponse;
import co.helpdesk.cesde.application.service.UserService;
import co.helpdesk.cesde.domain.exception.UserNotFoundException;
import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserJpaRepository usuarioRepository;

  @Override
  @Transactional
  public UserResponse activarUsuario(Long id) {

    UserEntity usuario = usuarioRepository
        .findById(id)
        .orElseThrow(
            () -> new UserNotFoundException(id));

    usuario.setActivo(true);

    UserEntity guardado =
        usuarioRepository.save(usuario);

    return mapToResponse(guardado);
  }

  @Override
  @Transactional
  public UserDeleteResponse eliminarUsuario(Long id) {

    UserEntity usuario = usuarioRepository
        .findById(id)
        .orElseThrow(
            () -> new UserNotFoundException(id));

    usuarioRepository.delete(usuario);

    return new UserDeleteResponse(
        "Usuario eliminado permanentemente");
  }

  @Transactional
  public UserResponse desactivarUsuario(Long id) {

    UserEntity usuario = usuarioRepository
        .findById(id)
        .orElseThrow(
            () -> new UserNotFoundException(id));

    usuario.setActivo(false);

    usuarioRepository.save(usuario);

    return mapToResponse(usuario);
  }

  private UserResponse mapToResponse(
      UserEntity usuario) {

    return new UserResponse(
        usuario.getId(),
        usuario.getNombreCompleto(),
        usuario.getEmail(),
        usuario.getRol().getNombre(),
        usuario.getActivo()
    );
  }

}


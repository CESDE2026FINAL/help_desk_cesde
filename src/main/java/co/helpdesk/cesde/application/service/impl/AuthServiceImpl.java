package co.helpdesk.cesde.application.service.impl;

import co.helpdesk.cesde.application.dto.auth.AuthResponse;
import co.helpdesk.cesde.application.dto.auth.LoginRequest;
import co.helpdesk.cesde.application.dto.auth.RegisterRequest;
import co.helpdesk.cesde.application.service.AuthService;
import co.helpdesk.cesde.domain.exception.DuplicateEmailException;
import co.helpdesk.cesde.infrastructure.persistence.entity.RoleEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.RoleJpaRepository;
import co.helpdesk.cesde.infrastructure.persistence.repository.UserJpaRepository;
import co.helpdesk.cesde.infrastructure.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  private final UserJpaRepository usuarioRepository;

  private final RoleJpaRepository rolRepository;

  private final JwtProvider jwtProvider;

  @Override
  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );

    UserEntity usuario = usuarioRepository
        .findByEmailAndActivoTrue(
            request.email())
        .orElseThrow(() ->
            new UsernameNotFoundException(
                "Usuario no encontrado o inactivo"));

    String token = jwtProvider.generateToken(usuario.getId(), usuario.getEmail(), usuario.getRol().getNombre());

    return new AuthResponse(token);
  }

  @Override
  @Transactional
  public AuthResponse register(RegisterRequest request) {
    if (usuarioRepository.findByEmail(request.email()).isPresent()) {
      throw new DuplicateEmailException("El email ya existe");
    }

    RoleEntity rolUsuario = rolRepository.findByNombre("ROLE_USUARIO").orElseThrow(() ->
        new IllegalArgumentException("El rol no existe"));

    UserEntity usuario = new UserEntity();

    usuario.setNombreCompleto(request.nombreCompleto());
    usuario.setEmail(request.email());
    usuario.setRol(rolUsuario);
    usuario.setPassword(passwordEncoder.encode(request.password()));
    usuario.setActivo(true);

    UserEntity usuarioGuardado = usuarioRepository.save(usuario);

    String token = jwtProvider.generateToken(usuarioGuardado.getId(), usuarioGuardado.getEmail(), usuarioGuardado.getRol().getNombre());

    return new AuthResponse(token);
  }
}

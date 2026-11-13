package co.helpdesk.cesde.infrastructure.security.service;

import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserJpaRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity usuario = usuarioRepository
        .findByEmailAndActivoTrue(email)
        .orElseThrow(()  -> new UsernameNotFoundException("Usuario no encontrado o inactivo" + email));

    return User.builder()
        .username(usuario.getEmail())
        .password(usuario.getPassword())
        .authorities(new SimpleGrantedAuthority(
            usuario.getRol().getNombre()))
        .disabled(!Boolean.TRUE.equals(usuario.getActivo()))
        .build();

  }
}

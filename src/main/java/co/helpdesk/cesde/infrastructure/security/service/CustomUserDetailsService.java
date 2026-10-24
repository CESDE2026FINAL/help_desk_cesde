package co.helpdesk.cesde.infrastructure.security.service;

import co.helpdesk.cesde.infrastructure.persistence.entity.UsuarioEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.UsuarioJpaRepository;
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

  private final UsuarioJpaRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UsuarioEntity usuario = usuarioRepository
        .findByEmail(email)
        .orElseThrow(()  -> new UsernameNotFoundException("Usuario no encontrado" + email));

    return User.builder()
        .username(usuario.getEmail())
        .password(usuario.getPassword())
        .authorities(new SimpleGrantedAuthority(
            usuario.getRol().getNombre()))
        .build();

  }
}

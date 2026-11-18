//package co.helpdesk.cesde.config;
//
//import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
//import co.helpdesk.cesde.infrastructure.persistence.repository.UserJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class PasswordMigrationRunner
//    implements CommandLineRunner {
//
//  private final UserJpaRepository userRepository;
//
//  private final PasswordEncoder passwordEncoder;
//
//  @Override
//  public void run(String... args) {
//
//    userRepository.findAll().forEach(usuario -> {
//
//      if (!usuario.getPassword().startsWith("$2a$")) {
//
//        usuario.setPassword(
//            passwordEncoder.encode(
//                usuario.getPassword()));
//
//        userRepository.save(usuario);
//
//        System.out.println(
//            "Password migrado: "
//                + usuario.getEmail());
//      }
//    });
//  }
//}
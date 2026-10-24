package co.helpdesk.cesde.application.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "El nombre es obligatorio")
    String nombreCompleto,

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6,
          message = "La contraseña debe tener mínimo seis caracteres")
    String password
) {
}

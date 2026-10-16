package com.orders.usuario;

import com.orders.auth.dto.SignupRequest;
import com.orders.usuario.dto.UsuarioRequest;
import com.orders.usuario.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class UsuarioMapper {

    public static Usuario toUsuario(SignupRequest signupRequest ) {
        Usuario usuario = new Usuario();
        usuario.setNombres(signupRequest.nombres());
        usuario.setApellidos(signupRequest.apellidos());
        usuario.setTelefono(signupRequest.telefono());
        usuario.setEmail(signupRequest.email());
        usuario.setPassword(signupRequest.password());
        return usuario;
    }


    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getTelefono(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()
        );
    }
}

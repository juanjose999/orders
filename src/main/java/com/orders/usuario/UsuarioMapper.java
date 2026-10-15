package com.orders.usuario;

import com.orders.usuario.dto.UsuarioRequest;
import com.orders.usuario.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class UsuarioMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static Usuario toUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombres(usuarioRequest.nombres());
        usuario.setApellidos(usuarioRequest.apellidos());
        usuario.setTelefono(usuarioRequest.telefono());
        usuario.setEmail(usuarioRequest.email());
        usuario.setPassword(passwordEncoder.enconde(usuarioRequest.password));
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

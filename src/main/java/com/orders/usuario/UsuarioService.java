package com.orders.usuario;
import com.orders.usuario.dto.UsuarioRequest;
import com.orders.usuario.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponse registrarUsuario(UsuarioRequest usuarioRequest) {
        validarEmailDisponible(usuarioRequest.email());
        Usuario usuario = UsuarioMapper.toUsuario(usuarioRequest);
        usuarioRepository.save(usuario);
        UsuarioResponse usuarioResponse = UsuarioMapper.toResponse(usuario);
        return usuarioResponse;
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));
    }

    public void validarEmailDisponible(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("Ya existe un usuario registrado con el correo: " + email);
        }
    }


}

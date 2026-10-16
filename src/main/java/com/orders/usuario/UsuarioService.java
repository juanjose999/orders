package com.orders.usuario;
import com.orders.auth.dto.AuthResponse;
import com.orders.auth.dto.SignupRequest;
import com.orders.security.JwtService;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public UsuarioResponse registrarUsuario(SignupRequest signupRequest) {
        validarEmailDisponible(signupRequest.email());
        Usuario usuario = UsuarioMapper.toUsuario(signupRequest);
        usuario.setPassword(passwordEncoder.encode(signupRequest.password()));
        usuario.setRol(Rol.ADMIN);

        usuarioRepository.save(usuario);
        UsuarioResponse usuarioResponse = UsuarioMapper.toResponse(usuario);
        return usuarioResponse;
    }

    public UsuarioResponse obtenerPorEmail(String email) {
        return UsuarioMapper.toResponse(
                usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email))
        );
    }

    public void validarEmailDisponible(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("Ya existe un usuario registrado con el correo: " + email);
        }
    }

}

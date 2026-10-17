package com.orders.direccion;

import com.orders.direccion.dto.DireccionRequest;
import com.orders.direccion.dto.DireccionResponse;
import com.orders.security.JwtService;
import com.orders.security.TokenType;
import com.orders.usuario.Usuario;
import com.orders.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    /**
     * Crea una nueva dirección para un usuario.
     */
    @Transactional
    public DireccionResponse crearDireccion(DireccionRequest request, String auth) {
        Usuario usuario = getUsuario(auth);

        Direccion direccion = DireccionMapper.toEntity(request, usuario);

        Direccion guardada = direccionRepository.save(direccion);

        return DireccionMapper.toResponse(guardada);
    }


    /**
     * Obtiene una dirección por su ID.
     */
    public DireccionResponse obtenerPorId(Long id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));
        return DireccionMapper.toResponse(direccion);
    }

    /**
     * Lista todas las direcciones asociadas a un usuario específico.
     */
    public List<DireccionResponse> listarPorUsuario(String auth) {
        Usuario usuario = getUsuario(auth);
        return direccionRepository.findByUsuarioId(usuario.getId()).stream()
                .map(DireccionMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza una dirección existente.
     */
    @Transactional
    public DireccionResponse actualizarDireccion(Long id, DireccionRequest request, String auth) {
        Usuario usuario = getUsuario(auth);
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));

        Direccion guardada = DireccionMapper.toEntity(request, usuario);

        Direccion actualizada = direccionRepository.save(direccion);

        return DireccionMapper.toResponse(actualizada);
    }

    /**
     * Elimina una dirección por su ID.
     */
    @Transactional
    public void eliminarDireccion(Long id) {
        if (!direccionRepository.existsById(id)) {
            throw new RuntimeException("Dirección no encontrada con ID: " + id);
        }
        direccionRepository.deleteById(id);
    }


    private Usuario getUsuario(String auth) {
        String token = auth.substring(7);
        String emailUser = jwtService.extractSubject(token, TokenType.ACCESS);

        Usuario usuario = usuarioRepository.findByEmail(emailUser)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo " + emailUser ));

        return usuario;
    }

}

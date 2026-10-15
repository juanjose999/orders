package com.orders.usuario.dto;

import com.orders.usuario.Rol;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nombres,
        String apellidos,
        String telefono,
        String email,
        Rol rol,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

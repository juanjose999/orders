package com.orders.direccion.dto;

public record DireccionResponse(
        Long id,
        Long usuarioId,
        String nombreDestinatario,
        String telefono,
        String direccionLinea,
        String detalleDireccion,
        String ciudad,
        String departamento
) {
}

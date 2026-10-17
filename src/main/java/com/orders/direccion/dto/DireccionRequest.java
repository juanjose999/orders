package com.orders.direccion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DireccionRequest(
        @NotBlank(message = "El nombre del destinatario es obligatorio")
        @Size(max = 160)
        String nombreDestinatario,

        @Size(max = 20)
        String telefono,

        @NotBlank(message = "El departamento es obligatorio")
        String departamento,

        @NotBlank(message = "La ciudad es obligatoria")
        String ciudad,

        @NotBlank(message = "La línea de dirección es obligatoria")
        String direccion,

        @NotBlank(message = "El detalle de direccion es obligatorio")
        String detalleDireccion
) {
}

package com.orders.direccion;

import com.orders.direccion.dto.DireccionRequest;
import com.orders.direccion.dto.DireccionResponse;
import com.orders.usuario.Usuario;

public class DireccionMapper {

    public static Direccion toEntity(DireccionRequest direccionRequest, Usuario usuario) {
        Direccion direccion = new Direccion();
        direccion.setUsuario(usuario);
        direccion.setNombreDestinatario(direccionRequest.nombreDestinatario());
        direccion.setTelefono(direccionRequest.telefono());
        direccion.setDireccionLinea(direccionRequest.direccion());
        direccion.setCiudad(direccionRequest.ciudad());
        direccion.setDepartamento(direccion.getDepartamento());
        return direccion;
    }

    public static DireccionResponse toResponse(Direccion direccion) {
        return new DireccionResponse(
                direccion.getId(),
                direccion.getUsuario().getId(),
                direccion.getNombreDestinatario(),
                direccion.getTelefono(),
                direccion.getDireccionLinea(),
                direccion.getDetalleDireccion(),
                direccion.getCiudad(),
                direccion.getDepartamento()
        );
    }
}

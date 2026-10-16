package com.orders.auth.dto;


import com.orders.usuario.dto.UsuarioResponse;

public record AuthResponse(
        Tokens tokens,
        UsuarioResponse user
) {
    public record Tokens(
            String accessToken,
            String refreshToken
    ) {
    }

}

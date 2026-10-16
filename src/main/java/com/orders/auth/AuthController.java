package com.orders.auth;

import com.orders.auth.dto.AuthResponse;
import com.orders.auth.dto.LoginRequest;
import com.orders.auth.dto.RefreshTokenRequest;
import com.orders.auth.dto.SignupRequest;
import com.orders.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /** Registra un gimnasio nuevo junto con su administrador. */
    @PostMapping("/admin/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AuthResponse> signupAdmin(@Valid @RequestBody SignupRequest signupRequest) {
        return ApiResponse.success(authService.registrarUsuario(signupRequest, true));
    }

    /** Registra un gimnasio nuevo junto con su administrador. */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AuthResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return ApiResponse.success(authService.registrarUsuario(signupRequest, false));
    }

    /** Login de administradores (correo y contraseña). */
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }


    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ApiResponse.success(authService.refrescar(request));
    }


}

package com.orders.product;

import com.orders.common.api.ApiResponse;
import com.orders.product.dto.ProductRequest;
import com.orders.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Lectura para cualquier usuario autenticado; crear, editar y desactivar solo ADMIN (ver SecurityConfig).
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<ProductResponse>> listar(@RequestParam(required = false) Long categoryId) {
        return ApiResponse.success(productService.listar(categoryId));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> obtener(@PathVariable Long id) {
        return ApiResponse.success(productService.obtenerPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> crear(@Valid @RequestBody ProductRequest request) {
        return ApiResponse.success(productService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ApiResponse.success(productService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> desactivar(@PathVariable Long id) {
        productService.desactivar(id);
        return ApiResponse.success(null);
    }
}

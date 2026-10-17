package com.orders.direccion;

import com.orders.direccion.dto.DireccionRequest;
import com.orders.direccion.dto.DireccionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionService direccionService;

    @PostMapping
    public ResponseEntity<DireccionResponse> create(@RequestBody DireccionRequest direccionRequest,
                                                    @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(direccionService.crearDireccion(direccionRequest, token), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DireccionResponse>> findByUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(direccionService.listarPorUsuario(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}

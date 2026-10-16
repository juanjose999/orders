package com.orders.product;

import com.orders.product.dto.ProductRequest;
import com.orders.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    /** Lista los productos activos; si se indica categoría, filtra por ella. */
    public List<ProductResponse> listar(Long categoryId) {
        List<Product> products = categoryId == null
                ? productRepository.findByActiveTrue()
                : productRepository.findByCategoryIdAndActiveTrue(categoryId);
        return products.stream().map(ProductMapper::toResponse).toList();
    }

    public ProductResponse obtenerPorId(Long id) {
        return ProductMapper.toResponse(buscar(id));
    }

    @Transactional
    public ProductResponse crear(ProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new RuntimeException("Ya existe un producto con el SKU: " + request.sku());
        }
        Product product = productRepository.save(ProductMapper.toProduct(request));
        return ProductMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse actualizar(Long id, ProductRequest request) {
        Product product = buscar(id);
        if (productRepository.existsBySkuAndIdNot(request.sku(), id)) {
            throw new RuntimeException("Ya existe un producto con el SKU: " + request.sku());
        }
        ProductMapper.updateProduct(product, request);
        // saveAndFlush para que la respuesta lleve el updatedAt asignado por la auditoría
        return ProductMapper.toResponse(productRepository.saveAndFlush(product));
    }

    /** Baja lógica: el producto queda inactivo pero se conserva para el historial de pedidos. */
    @Transactional
    public void desactivar(Long id) {
        buscar(id).setActive(false);
    }

    private Product buscar(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
}

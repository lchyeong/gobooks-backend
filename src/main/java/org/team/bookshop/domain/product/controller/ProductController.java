package org.team.bookshop.domain.product.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.bookshop.domain.product.dto.AddProductRequest;
import org.team.bookshop.domain.product.dto.ProductResponse;
import org.team.bookshop.domain.product.dto.UpdateProductRequest;
import org.team.bookshop.domain.product.entity.Product;
import org.team.bookshop.domain.product.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody @Valid AddProductRequest request) {
    Product savedProduct = productService.createProduct(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> findAllProducts() {
    List<ProductResponse> products = productService.findAll();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> findProduct(@PathVariable Long id) {
    ProductResponse product = productService.findById(id);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable long id,
      @Valid @RequestBody UpdateProductRequest request) {
    Product updatedProduct = productService.update(id, request);
    return ResponseEntity.ok(updatedProduct);
  }

  // 특정 카테고리 조회
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<ProductResponse>> getProductsByCategory(
      @PathVariable Long categoryId) {
    List<ProductResponse> productResponses = productService.getProductsByCategoryId(categoryId);
    return ResponseEntity.ok(productResponses);
  }
}


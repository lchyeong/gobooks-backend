package org.team.bookshop.domain.product.service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.bookshop.domain.category.entity.BookCategory;
import org.team.bookshop.domain.category.entity.BookCategoryId;
import org.team.bookshop.domain.category.entity.Category;
import org.team.bookshop.domain.category.repository.CategoryRepository;
import org.team.bookshop.domain.product.dto.AddProductRequest;
import org.team.bookshop.domain.product.dto.ProductDto;
import org.team.bookshop.domain.product.dto.ProductResponse;
import org.team.bookshop.domain.product.dto.UpdateProductRequest;
import org.team.bookshop.domain.product.entity.Product;
import org.team.bookshop.domain.product.repository.ProductRepository;
import org.team.bookshop.global.error.ErrorCode;
import org.team.bookshop.global.error.exception.ApiException;
import org.team.bookshop.global.error.exception.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  //대량일때는 성능이슈
  @Transactional
  public AddProductRequest createProduct(AddProductRequest requestDto) {
    Product product = productRepository.save(requestDto.toEntity());

    Set<BookCategory> bookCategories = requestDto.getCategoryIds().stream()
        .map(categoryId -> {
          Category category = categoryRepository.findById(categoryId)
              .orElseThrow(() -> new ApiException(ErrorCode.ENTITY_NOT_FOUND));
          BookCategory bookCategory = new BookCategory(
              new BookCategoryId(product.getId(), category.getId()),
              product,
              category
          );

          product.getBookCategories().add(bookCategory);

          return bookCategory;
        })
        .collect(Collectors.toSet());

    return new AddProductRequest(product);
  }

  public List<ProductResponse> findAll() {
    return productRepository.findAll().stream()
        .map(ProductResponse::new)
        .collect(Collectors.toList());
  }

  public ProductResponse findById(long id) {
    return productRepository.findById(id)
        .map(ProductResponse::new)
        .orElseThrow(() -> new EntityNotFoundException("not found: " + id));
  }

  public void delete(long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("not found: " + id));
    productRepository.delete(product);
  }

  @Transactional
  public Product update(long id, UpdateProductRequest request) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("not found: " + id));
    product.update(
        request.getTitle(),
        request.getAuthor(),
        request.getIsbn(),
        request.getContent(),
        request.getFixedPrice(),
        request.getPublicationYear(),
        request.getStatus()

    );
    return product;
  }

//    public List<ProductResponse> getProductsByCategoryId(Long categoryId) {
//        List<Product> products = productRepository.findByCategoryIds(categoryId);
//        return products.stream()
//            .map(ProductResponse::new)
//            .collect(Collectors.toList());
//    }
//
//    public Page<ProductResponse> getProductsByCategoryId(Long categoryId, Pageable pageable) {
//        Page<Product> products = productRepository.findByCategoryIds(categoryId, pageable);
//        return products.map(ProductResponse::new);
//    }

  // 카테고리별 조회 paging, querydsl
  public Page<ProductDto> getProductsByCategoryId(Long categoryId, Pageable pageable) {
    return productRepository.findByCategoryIds(categoryId, pageable);
  }
}

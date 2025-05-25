package com.marketplace.service;

import com.marketplace.mapper.ProductMapper;
import com.marketplace.model.dto.ProductDTO;
import com.marketplace.model.dto.ProductRequestDTO;
import com.marketplace.model.entity.Category;
import com.marketplace.model.entity.Product;
import com.marketplace.repository.CategoryRepository;
import com.marketplace.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    private static final String PRODUCT_CACHE = "products";
    private static final String CATEGORY_CACHE = "categories";

    @Cacheable(value = PRODUCT_CACHE)
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = PRODUCT_CACHE, key = "#productId")
    public ProductDTO getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @CacheEvict(cacheNames = {PRODUCT_CACHE, CATEGORY_CACHE}, allEntries = true)
    @Transactional
    public ProductDTO createProduct(ProductRequestDTO request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @CacheEvict(cacheNames = {PRODUCT_CACHE, CATEGORY_CACHE}, allEntries = true)
    @Transactional
    public ProductDTO updateProduct(ProductRequestDTO request) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getId()));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    @CacheEvict(cacheNames = PRODUCT_CACHE, allEntries = true)
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
    }
}

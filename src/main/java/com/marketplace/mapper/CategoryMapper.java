package com.marketplace.mapper;

import com.marketplace.model.entity.Category;
import com.marketplace.model.entity.Product;
import com.marketplace.model.dto.CategoryDTO;
import com.marketplace.model.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ProductMapper productMapper;

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getCategoryName(),
                mapProducts(category.getProducts())
        );
    }

    private List<ProductDTO> mapProducts(List<Product> products) {
        if (products == null) return List.of();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}

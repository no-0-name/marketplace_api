package com.marketplace.service;

import com.marketplace.mapper.CategoryMapper;
import com.marketplace.model.dto.CategoryDTO;
import com.marketplace.model.dto.CategoryRequestDTO;
import com.marketplace.model.entity.Category;
import com.marketplace.repository.CategoryRepository;
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
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private static final String CATEGORIES_CACHE = "categories";

    @Cacheable(value = CATEGORIES_CACHE)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = CATEGORIES_CACHE, key = "#categoryId")
    public CategoryDTO getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
    }

    @CacheEvict(cacheNames = CATEGORIES_CACHE, allEntries = true)
    @Transactional
    public CategoryDTO createCategory(CategoryRequestDTO request) {
        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new IllegalArgumentException("Category already exists with name: " + request.getCategoryName());
        }

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @CacheEvict(cacheNames = CATEGORIES_CACHE, allEntries = true)
    @Transactional
    public CategoryDTO updateCategory(CategoryRequestDTO request) {
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getId()));

        if (!category.getCategoryName().equals(request.getCategoryName()) &&
                categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new IllegalArgumentException("Category already exists with name: " + request.getCategoryName());
        }

        category.setCategoryName(request.getCategoryName());

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @CacheEvict(cacheNames = CATEGORIES_CACHE, allEntries = true)
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        categoryRepository.delete(category);
    }
}

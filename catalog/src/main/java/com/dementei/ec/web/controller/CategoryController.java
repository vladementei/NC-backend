package com.dementei.ec.web.controller;

import com.dementei.ec.dto.CategoryDto;
import com.dementei.ec.entity.Category;
import com.dementei.ec.mapper.CategoryMapper;
import com.dementei.ec.service.CategoryService;
import com.dementei.ec.service.DefaultCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(DefaultCategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.OK);
    }

    @PostMapping("/saveCategories")
    public ResponseEntity<List<CategoryDto>> saveCategories(@RequestBody List<CategoryDto> categoryDtoList) {
        Set<Category> categories = new HashSet<>();
        categoryDtoList.forEach(categoryDto -> categories.add(categoryMapper.toEntity(categoryDto)));
        Set<Category> savedCategories = categoryService.saveCategories(categories);
        List<CategoryDto> savedCategoryDtoList = savedCategories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(savedCategoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryDto> findCategory(@PathVariable("id") long id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<CategoryDto> findCategory(@PathVariable("name") String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PutMapping("/updateName")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        Category updatedCategory = categoryService.updateCategoryName(categoryDto.getId(), categoryDto.getCategory());
        CategoryDto updatedCategoryDto = categoryMapper.toDto(updatedCategory);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
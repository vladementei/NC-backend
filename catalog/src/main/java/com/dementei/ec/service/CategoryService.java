package com.dementei.ec.service;

import com.dementei.ec.entity.Category;

import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category);

    Set<Category> saveCategories(Set<Category> categories);

    Category getCategoryById(long id);

    Category getCategoryByName(String categoryName);

    Category updateCategory(Category update);

    void deleteCategory(Category category);

}

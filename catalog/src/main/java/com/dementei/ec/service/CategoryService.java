package com.dementei.ec.service;

import com.dementei.ec.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category);

    Set<Category> saveCategories(Set<Category> categories);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category getCategoryByName(String categoryName);

    Category updateCategoryName(long id, String newCategoryName);

    void deleteCategory(long id);

}

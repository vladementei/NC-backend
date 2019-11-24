package com.dementei.ec.mapper;

import com.dementei.ec.dto.CategoryDto;
import com.dementei.ec.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryMapper {
    private final ModelMapper mapper;

    @Autowired
    public CategoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Category toEntity(CategoryDto categoryDto) {
        Category category = Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, Category.class);
        return category;
    }

    public CategoryDto toDto(Category entity) {
        CategoryDto categoryDto = Objects.isNull(entity) ? null : mapper.map(entity, CategoryDto.class);
        return categoryDto;
    }
}

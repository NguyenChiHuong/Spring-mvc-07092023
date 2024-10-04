package com.javaweb.converter;

import org.springframework.stereotype.Component;

import com.javaweb.dto.CategoryDTO;
import com.javaweb.entity.CategoryEntity;

@Component
public class CategoryConvert {
	public CategoryDTO toDto(CategoryEntity entity){
		CategoryDTO result = new CategoryDTO();
		result.setId(entity.getId());
		result.setCode(entity.getCode());
		result.setName(entity.getName());
		return result;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto){
		CategoryEntity result = new CategoryEntity();
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		return result;
	}
}

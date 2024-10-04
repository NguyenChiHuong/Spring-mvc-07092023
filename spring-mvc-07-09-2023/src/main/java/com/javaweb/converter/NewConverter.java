package com.javaweb.converter;

import org.springframework.stereotype.Component;

import com.javaweb.dto.NewDTO;
import com.javaweb.entity.NewEntity;

@Component
public class NewConverter {
	
	//@Autowired
	//private ModelMapper modelMapper;
	
	public NewDTO toDto(NewEntity entity) {

		NewDTO result = new NewDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setThumbnail(entity.getThumbnail());
		result.setContent(entity.getContent());
		result.setCategoryCode(entity.getCategory().getCode());
		return result;

		  //return modelMapper.map(entity, NewDTO.class);
	}
	
	public NewEntity toEntity(NewDTO dto) {

		NewEntity result = new NewEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setThumbnail(dto.getThumbnail());
		result.setContent(dto.getContent());
		return result;

		//return modelMapper.map(dto, NewEntity.class);
	}
	
	public NewEntity toEntity(NewEntity entity,NewDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumbnail(dto.getThumbnail());
		entity.setContent(dto.getContent());
		return entity;
	}
}

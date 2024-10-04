package com.javaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaweb.dto.NewDTO;

@Service
public interface INewService {
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem();
	NewDTO findById(long id);
	NewDTO save(NewDTO dto);
	void delete(long[] ids);
	//void saveImage(NewDTO newDTO, NewEntity newEntity);
}
